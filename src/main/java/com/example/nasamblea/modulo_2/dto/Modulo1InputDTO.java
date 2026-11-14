package com.example.nasamblea.modulo_2.dto;

public class Modulo1InputDTO {
    private String parcelId;
    private double latitude;
    private double longitude;
    private String timestamp;
    private String cultivo;           // "maiz", "alfalfa", "hortalizas"
    private String etapaFenologica;   // "inicial", "desarrollo", "mediados", "madurez"
    private double capacidadCampoMm;  // Ej. 120.0 mm por defecto
    private double riegoAplicadoMm;

    // Datos Satelitales y Pronóstico
    private double smapRootzoneMoisturePct; // Humedad inicial (%)
    private double gpmPrecip24hMm;          // Lluvia últimas 24h (mm)
    private double tMax;                    // Temperatura máxima (°C)
    private double tMin;                    // Temperatura mínima (°C)
    private double precip72hAccumMm;        // Pronóstico lluvia 72h (mm)
    private double precipTotalForecastMm;   // Pronóstico total 7-14 días (mm)
    private boolean enZonaDrenaje;          // Capa GIS: Drenaje e Influencia

    // Getters y Setters
    public String getParcelId() { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getCultivo() { return cultivo; }
    public void setCultivo(String cultivo) { this.cultivo = cultivo; }
    public String getEtapaFenologica() { return etapaFenologica; }
    public void setEtapaFenologica(String etapaFenologica) { this.etapaFenologica = etapaFenologica; }
    public double getCapacidadCampoMm() { return capacidadCampoMm > 0 ? capacidadCampoMm : 120.0; }
    public void setCapacidadCampoMm(double capacidadCampoMm) { this.capacidadCampoMm = capacidadCampoMm; }
    public double getRiegoAplicadoMm() { return riegoAplicadoMm; }
    public void setRiegoAplicadoMm(double riegoAplicadoMm) { this.riegoAplicadoMm = riegoAplicadoMm; }
    public double getSmapRootzoneMoisturePct() { return smapRootzoneMoisturePct; }
    public void setSmapRootzoneMoisturePct(double smapRootzoneMoisturePct) { this.smapRootzoneMoisturePct = smapRootzoneMoisturePct; }
    public double getGpmPrecip24hMm() { return gpmPrecip24hMm; }
    public void setGpmPrecip24hMm(double gpmPrecip24hMm) { this.gpmPrecip24hMm = gpmPrecip24hMm; }
    public double getTMax() { return tMax; }
    public void setTMax(double tMax) { this.tMax = tMax; }
    public double getTMin() { return tMin; }
    public void setTMin(double tMin) { this.tMin = tMin; }
    public double getPrecip72hAccumMm() { return precip72hAccumMm; }
    public void setPrecip72hAccumMm(double precip72hAccumMm) { this.precip72hAccumMm = precip72hAccumMm; }
    public double getPrecipTotalForecastMm() { return precipTotalForecastMm; }
    public void setPrecipTotalForecastMm(double precipTotalForecastMm) { this.precipTotalForecastMm = precipTotalForecastMm; }
    public boolean isEnZonaDrenaje() { return enZonaDrenaje; }
    public void setEnZonaDrenaje(boolean enZonaDrenaje) { this.enZonaDrenaje = enZonaDrenaje; }
}