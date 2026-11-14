package com.example.nasamblea.modulo_2.dto;

import com.example.nasamblea.modulo_2.dto.Modulo1InputDTO;
import com.example.nasamblea.modulo_2.dto.ParcelStatusResponse;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Map;

@Service
public class MotorAgroclimaticoService {

    // Tabla de Coeficientes Kc por cultivo local de Cochabamba y etapa fenológica (FAO-56)
    private static final Map<String, Map<String, Double>> KC_CULTIVOS = Map.of(
        "maiz", Map.of(
            "inicial", 0.40,
            "desarrollo", 0.80,
            "mediados", 1.15,
            "madurez", 0.60
        ),
        "alfalfa", Map.of(
            "inicial", 0.40,
            "desarrollo", 0.85,
            "mediados", 1.15,
            "madurez", 1.10
        ),
        "hortalizas", Map.of(
            "inicial", 0.50,
            "desarrollo", 0.80,
            "mediados", 1.05,
            "madurez", 0.85
        )
    );

    /**
     * Calcula la Radiación Extraterrestre (Ra) en MJ m^-2 dia^-1 según FAO-56 (Ec. 21).
     */
    public double calcularRadiacionExtraterrestreRa(double latitudDeg, int diaJuliano) {
        double latRad = Math.toRadians(latitudDeg);
        double dr = 1.0 + 0.033 * Math.cos((2.0 * Math.PI / 365.0) * diaJuliano);
        double declinacionSolar = 0.409 * Math.sin((2.0 * Math.PI / 365.0) * diaJuliano - 1.39);

        double tanProd = -Math.tan(latRad) * Math.tan(declinacionSolar);
        tanProd = Math.max(-1.0, Math.min(1.0, tanProd));
        double ws = Math.acos(tanProd);

        double gsc = 0.0820; // Constante solar
        double ra = ((24.0 * 60.0) / Math.PI) * gsc * dr * (
            ws * Math.sin(latRad) * Math.sin(declinacionSolar) +
            Math.cos(latRad) * Math.cos(declinacionSolar) * Math.sin(ws)
        );
        return Math.max(0.0, ra);
    }

    /**
     * Calcula Evapotranspiración de Referencia ET0 (mm/día) por FAO Hargreaves-Samani.
     */
    public double calcularEt0Hargreaves(double tMax, double tMin, double latitud, String timestampIso) {
        double tMean = (tMax + tMin) / 2.0;
        double diffTemp = Math.max(0.0, tMax - tMin);

        int diaJuliano = ZonedDateTime.parse(timestampIso).getDayOfYear();
        double ra = calcularRadiacionExtraterrestreRa(latitud, diaJuliano);

        double et0 = 0.0023 * (tMean + 17.8) * Math.sqrt(diffTemp) * (0.408 * ra);
        return Math.round(et0 * 100.0) / 100.0;
    }

    /**
     * Obtiene el coeficiente Kc según cultivo local y etapa fenológica.
     */
    public double obtenerKc(String cultivo, String etapa) {
        if (cultivo == null || etapa == null) return 1.00;
        String cNorm = cultivo.toLowerCase().trim();
        String eNorm = etapa.toLowerCase().trim();

        return KC_CULTIVOS.getOrDefault(cNorm, Map.of()).getOrDefault(eNorm, 1.00);
    }

    /**
     * Procesa el Balance Hídrico Diario y evalúa la Matriz de Riesgo por parcela.
     */
    public ParcelStatusResponse procesarBalanceYRiesgo(Modulo1InputDTO input) {
        double ccMm = input.getCapacidadCampoMm();

        // 1. Cálculo de ET0 y ETc
        double et0 = calcularEt0Hargreaves(input.getTMax(), input.getTMin(), input.getLatitude(), input.getTimestamp());
        double kc = obtenerKc(input.getCultivo(), input.getEtapaFenologica());
        double etcDiaria = Math.round((et0 * kc) * 100.0) / 100.0;

        // 2. Balance Hídrico Diario: SW_t = SW_{t-1} + P_t + R_t - ETc_t - D_t - Es_t
        double swAnterior = (input.getSmapRootzoneMoisturePct() / 100.0) * ccMm;
        double lluvia24h = input.getGpmPrecip24hMm();
        double escorrentia = (lluvia24h > 25.0) ? (lluvia24h - 25.0) * 0.30 : 0.0;

        double swBruto = swAnterior + lluvia24h + input.getRiegoAplicadoMm() - etcDiaria - escorrentia;
        double swActual = Math.max(0.0, Math.min(ccMm, swBruto));

        double humedadBalancePct = Math.round(((swActual / ccMm) * 100.0) * 100.0) / 100.0;
        double lluvia72h = input.getPrecip72hAccumMm();

        // 3. Evaluación de la Matriz de Riesgo y Reglas de Negocio
        ParcelStatusResponse response = new ParcelStatusResponse();
        response.setParcelId(input.getParcelId());
        response.setSoilMoisturePct(humedadBalancePct);
        response.setEt0MmDia(et0);
        response.setKcAplicado(kc);
        response.setEtcCultivoMmDia(etcDiaria);

        // Regla 1: Riesgo de Anegamiento (> 90% humedad y lluvia prevista >= 15 mm)
        if (humedadBalancePct > 90.0 && (lluvia72h >= 15.0 || input.getPrecipTotalForecastMm() >= 30.0)) {
            response.setEstadoParcela("Riesgo de Anegamiento");
            response.setSemaphoreColor("BLUE");
            response.setPumpAction("LOW");
            response.setAlertaInundacionGamc(true);
            response.setIrrigationDepthMm(0.0);
            response.setWarningMessage(String.format(
                "ALERTA DE ANEGAMIENTO: Suelo saturado (%.1f%%) y pronóstico de %.1f mm de lluvia. Cerrar compuertas.",
                humedadBalancePct, lluvia72h
            ));
        }
        // Regla 2: Riego Urgente (< 40% humedad)
        else if (humedadBalancePct < 40.0) {
            if (lluvia72h >= 12.0) {
                response.setEstadoParcela("Estrés Hídrico Leve");
                response.setSemaphoreColor("YELLOW");
                response.setPumpAction("LOW");
                response.setAlertaInundacionGamc(false);
                response.setIrrigationDepthMm(0.0);
                response.setWarningMessage(String.format(
                    "Humedad baja (%.1f%%), pero se esperan %.1f mm de lluvia en 72h. Esperar precipitación.",
                    humedadBalancePct, lluvia72h
                ));
            } else {
                double laminaNecesaria = Math.round(((0.75 * ccMm) - swActual) * 10.0) / 10.0;
                response.setEstadoParcela("Riego Urgente");
                response.setSemaphoreColor("RED");
                response.setPumpAction("HIGH"); // Ordena encender bomba en ESP32
                response.setAlertaInundacionGamc(false);
                response.setIrrigationDepthMm(Math.max(0.0, laminaNecesaria));
                response.setWarningMessage(String.format(
                    "RIEGO URGENTE: Humedad crítica (%.1f%%) en %s. Aplicar lámina de %.1f mm.",
                    humedadBalancePct, input.getCultivo(), laminaNecesaria
                ));
            }
        }
        // Regla 3: Estrés Hídrico Leve (40% a 55%)
        else if (humedadBalancePct <= 55.0) {
            double laminaPreventiva = Math.round(((0.75 * ccMm) - swActual) * 10.0) / 10.0;
            response.setEstadoParcela("Estrés Hídrico Leve");
            response.setSemaphoreColor("YELLOW");
            response.setPumpAction("LOW");
            response.setAlertaInundacionGamc(false);
            response.setIrrigationDepthMm(Math.max(0.0, laminaPreventiva));
            response.setWarningMessage(String.format(
                "Precaución: Humedad descendiendo (%.1f%%). Programar riego preventivo de %.1f mm.",
                humedadBalancePct, laminaPreventiva
            ));
        }
        // Regla 4: Óptimo (55% a 90%)
        else {
            response.setEstadoParcela("Óptimo");
            response.setSemaphoreColor("GREEN");
            response.setPumpAction("LOW");
            response.setAlertaInundacionGamc(false);
            response.setIrrigationDepthMm(0.0);
            response.setWarningMessage("Humedad óptima en zona radicular. No se requiere riego hoy.");
        }

        return response;
    }
}