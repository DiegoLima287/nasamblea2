package com.example.nasamblea.modulo_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Modulo1InputDTO {
    @JsonProperty("parcel_id")
    private String parcelId;
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("cultivo")
    private String cultivo;
    @JsonProperty("etapa_fenologica")
    private String etapaFenologica;
    @JsonProperty("capacidad_campo_mm")
    private double capacidadCampoMm;
    @JsonProperty("riego_aplicado_mm")
    private double riegoAplicadoMm;

    // Datos Satelitales y Pronóstico
    @JsonProperty("smap_rootzone_moisture_pct")
    private double smapRootzoneMoisturePct;
    @JsonProperty("gpm_precip_24h_mm")
    private double gpmPrecip24hMm;
    @JsonProperty("t_max")
    private double tMax;
    @JsonProperty("t_min")
    private double tMin;
    @JsonProperty("precip_72h_accum_mm")
    private double precip72hAccumMm;
    @JsonProperty("precip_total_forecast_mm")
    private double precipTotalForecastMm;
    @JsonProperty("en_zona_drenaje")
    private boolean enZonaDrenaje;

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