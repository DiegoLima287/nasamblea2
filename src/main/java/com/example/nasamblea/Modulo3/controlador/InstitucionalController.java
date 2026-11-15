package com.example.nasamblea.Modulo3.controlador;

import com.example.nasamblea.Modulo3.DTO.AlertaInstitucionalDTO;
import com.example.nasamblea.Modulo3.servicio.GestionInstitucionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamc")
public class InstitucionalController {

    private final GestionInstitucionalService gestionInstitucionalService;

    public InstitucionalController(GestionInstitucionalService gestionInstitucionalService) {
        this.gestionInstitucionalService = gestionInstitucionalService;
    }

    @GetMapping("/alertas")
    public ResponseEntity<List<AlertaInstitucionalDTO>> obtenerAlertasInstitucionales() {
        List<AlertaInstitucionalDTO> alertas = gestionInstitucionalService.generarAlertasDistritales();
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/drenaje/{canalId}/alerta")
    public ResponseEntity<AlertaInstitucionalDTO> verificarCanalDrenaje(@PathVariable String canalId) {
        AlertaInstitucionalDTO alerta = gestionInstitucionalService.verificarCanalYGenerarAlerta(canalId);
        return ResponseEntity.ok(alerta);
    }
}