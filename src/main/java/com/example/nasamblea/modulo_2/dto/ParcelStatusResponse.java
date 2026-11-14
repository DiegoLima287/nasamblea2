package com.example.nasamblea.modulo_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParcelStatusResponse {

    @JsonProperty("parcel_id")
    private String parcelId;

    @JsonProperty("estado_parcela")
    private String estadoParcela; // "Riesgo de Anegamiento", "Riego Urgente", "Estrés Hídrico Leve", "Óptimo"

    @JsonProperty("semaphore_color")
    private String semaphoreColor; // "BLUE", "RED", "YELLOW", "GREEN"

    @JsonProperty("pump_action")
    private String pumpAction; // "HIGH" o "LOW" (Consumo directo por ESP32)

    @JsonProperty("soil_moisture_pct")
    private double soilMoisturePct;

    @JsonProperty("et0_mm_dia")
    private double et0MmDia;

    @JsonProperty("kc_aplicado")
    private double kcAplicado;

    @JsonProperty("etc_cultivo_mm_dia")
    private double etcCultivoMmDia;

    @JsonProperty("irrigation_depth_mm")
    private double irrigationDepthMm;

    @JsonProperty("alerta_inundacion_gamc")
    private boolean alertaInundacionGamc;

    @JsonProperty("warning_message")
    private String warningMessage;

    // Getters y Setters
    public String getParcelId() { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }
    public String getEstadoParcela() { return estadoParcela; }
    public void setEstadoParcela(String estadoParcela) { this.estadoParcela = estadoParcela; }
    public String getSemaphoreColor() { return semaphoreColor; }
    public void setSemaphoreColor(String semaphoreColor) { this.semaphoreColor = semaphoreColor; }
    public String getPumpAction() { return pumpAction; }
    public void setPumpAction(String pumpAction) { this.pumpAction = pumpAction; }
    public double getSoilMoisturePct() { return soilMoisturePct; }
    public void setSoilMoisturePct(double soilMoisturePct) { this.soilMoisturePct = soilMoisturePct; }
    public double getEt0MmDia() { return et0MmDia; }
    public void setEt0MmDia(double et0MmDia) { this.et0MmDia = et0MmDia; }
    public double getKcAplicado() { return kcAplicado; }
    public void setKcAplicado(double kcAplicado) { this.kcAplicado = kcAplicado; }
    public double getEtcCultivoMmDia() { return etcCultivoMmDia; }
    public void setEtcCultivoMmDia(double etcCultivoMmDia) { this.etcCultivoMmDia = etcCultivoMmDia; }
    public double getIrrigationDepthMm() { return irrigationDepthMm; }
    public void setIrrigationDepthMm(double irrigationDepthMm) { this.irrigationDepthMm = irrigationDepthMm; }
    public boolean isAlertaInundacionGamc() { return alertaInundacionGamc; }
    public void setAlertaInundacionGamc(boolean alertaInundacionGamc) { this.alertaInundacionGamc = alertaInundacionGamc; }
    public String getWarningMessage() { return warningMessage; }
    public void setWarningMessage(String warningMessage) { this.warningMessage = warningMessage; }
}