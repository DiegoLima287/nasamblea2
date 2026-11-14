package com.example.nasamblea.Modulo_1_Ingesta_GIS.src.com.fieldshift;

import java.util.HashMap;
import java.util.Map;

public class GestorGisLocal {
    public static Map<String, Object> obtenerContextoGis(double lat, double lon) {
        Map<String, Object> gisData = new HashMap<>();

        // Simulación de cruce espacial en base a polígonos municipales de Cochabamba
        // (En producción se puede conectar a PostGIS o evaluar geometrías con GeoTools
        // / Shapely)
        if (lat <= -17.35 && lon <= -66.12) {
            gisData.put("elevation_m", 2558);
            gisData.put("district_id", "Distrito 9");
            gisData.put("subalcaldia", "Itocta");
            gisData.put("land_use_zone", "Agrícola Periurbano Protegido");
            gisData.put("channel_name", "Canal Acequia Coña Coña");
            gisData.put("distance_m", 45.2);
            gisData.put("overflow_risk_zone", true);
        } else {
            gisData.put("elevation_m", 2570);
            gisData.put("district_id", "Distrito 7");
            gisData.put("subalcaldia", "Aleid");
            gisData.put("land_use_zone", "Zona Agrícola Tradicional");
            gisData.put("channel_name", "Torrentera Pajcha");
            gisData.put("distance_m", 120.0);
            gisData.put("overflow_risk_zone", false);
        }

        return gisData;
    }
}
