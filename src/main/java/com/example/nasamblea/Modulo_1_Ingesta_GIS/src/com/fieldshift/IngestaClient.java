package com.example.nasamblea.Modulo_1_Ingesta_GIS.src.com.fieldshift;

import com.example.nasamblea.modulo_2.dto.Modulo1InputDTO;
import org.springframework.stereotype.Service;

@Service
public class IngestaClient {

    public Modulo1InputDTO generarInputDesdeCoordenadas(String parcelId, double latitude, double longitude) {
        Modulo1InputDTO input = new Modulo1InputDTO();
        input.setParcelId(parcelId);
        input.setLatitude(latitude);
        input.setLongitude(longitude);
        input.setTimestamp("2026-11-14T08:00:00Z");
        input.setCultivo("maiz");
        input.setEtapaFenologica("mediados");
        input.setCapacidadCampoMm(120.0);
        input.setRiegoAplicadoMm(0.0);
        input.setSmapRootzoneMoisturePct(38.0);
        input.setGpmPrecip24hMm(0.0);
        input.setTMax(27.5);
        input.setTMin(11.5);
        input.setPrecip72hAccumMm(1.5);
        input.setPrecipTotalForecastMm(15.0);
        input.setEnZonaDrenaje(false);
        return input;
    }
}
