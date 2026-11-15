package com.example.nasamblea.Modulo3.DTO;

import java.time.Instant;

public class AlertaInstitucionalDTO {
    private String distrito;
    private String tipoAlerta; // Ej: "RIESGO_ANEGAMIENTO", "ESTRES_HIDRICO"
    private String nivelSeveridad; // "AZUL", "ROJO"
    private int parcelasAfectadas;
    private double porcentajeAfectacion;
    private String mensajeAlerta;
    private Instant timestamp;

    public AlertaInstitucionalDTO() {}

    public AlertaInstitucionalDTO(String distrito, String tipoAlerta, String nivelSeveridad, 
                                  int parcelasAfectadas, double porcentajeAfectacion, String mensajeAlerta) {
        this.distrito = distrito;
        this.tipoAlerta = tipoAlerta;
        this.nivelSeveridad = nivelSeveridad;
        this.parcelasAfectadas = parcelasAfectadas;
        this.porcentajeAfectacion = porcentajeAfectacion;
        this.mensajeAlerta = mensajeAlerta;
        this.timestamp = Instant.now();
    }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }

    public String getNivelSeveridad() { return nivelSeveridad; }
    public void setNivelSeveridad(String nivelSeveridad) { this.nivelSeveridad = nivelSeveridad; }

    public int getParcelasAfectadas() { return parcelasAfectadas; }
    public void setParcelasAfectadas(int parcelasAfectadas) { this.parcelasAfectadas = parcelasAfectadas; }

    public double getPorcentajeAfectacion() { return porcentajeAfectacion; }
    public void setPorcentajeAfectacion(double porcentajeAfectacion) { this.porcentajeAfectacion = porcentajeAfectacion; }

    public String getMensajeAlerta() { return mensajeAlerta; }
    public void setMensajeAlerta(String mensajeAlerta) { this.mensajeAlerta = mensajeAlerta; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
