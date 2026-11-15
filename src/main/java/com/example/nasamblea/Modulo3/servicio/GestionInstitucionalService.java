package com.example.nasamblea.Modulo3.servicio;
import com.example.nasamblea.Modulo3.DTO.AlertaInstitucionalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestionInstitucionalService {

    // Simula la consolidación de datos por distrito utilizando capas GIS
    public List<AlertaInstitucionalDTO> generarAlertasDistritales() {
        List<AlertaInstitucionalDTO> alertas = new ArrayList<>();
        
        alertas.add(new AlertaInstitucionalDTO(
            "Distrito 5 - Periurbana Norte",
            "RIESGO_ANEGAMIENTO",
            "AZUL",
            12,
            18.5,
            "Acumulación crítica detectada en red de drenaje principal. Se recomienda cierre preventivo de compuertas upstream."
        ));
        
        alertas.add(new AlertaInstitucionalDTO(
            "Distrito 2 - Sud",
            "ESTRES_HIDRICO",
            "ROJO",
            25,
            34.2,
            "Déficit hídrico prolongado en sector agrícola. Requiere prioridad en programación de riego."
        ));

        return alertas;
    }

    // Verifica el estado de un canal específico y despacha la alerta al GAMC y regantes
    public AlertaInstitucionalDTO verificarCanalYGenerarAlerta(String canalId) {
        return new AlertaInstitucionalDTO(
            "Canal Principal " + canalId,
            "CONTROL_COMPUERTAS",
            "AZUL",
            8,
            15.0,
            "Alerta automatizada despachada a la central del GAMC y al juez de aguas de la asociación para maniobra de compuertas."
        );
    }
}