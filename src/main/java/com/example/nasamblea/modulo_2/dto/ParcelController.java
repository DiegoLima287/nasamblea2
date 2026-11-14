package com.example.nasamblea.modulo_2.dto;

import com.example.nasamblea.Modulo_1_Ingesta_GIS.src.com.fieldshift.IngestaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parcel")
public class ParcelController {

    private final MotorAgroclimaticoService motorAgroclimaticoService;
    private final IngestaClient ingestaClient;

    public ParcelController(MotorAgroclimaticoService motorAgroclimaticoService, IngestaClient ingestaClient) {
        this.motorAgroclimaticoService = motorAgroclimaticoService;
        this.ingestaClient = ingestaClient;
    }

    // Endpoint GET consumido directamente por el ESP32 y la vista Farmers-First
    @GetMapping("/{parcelId}/status")
    public ResponseEntity<ParcelStatusResponse> getParcelStatus(@PathVariable String parcelId) {
        Modulo1InputDTO datosModulo1 = ingestaClient.generarInputDesdeCoordenadas(parcelId, -17.3895, -66.1568);
        ParcelStatusResponse resultado = motorAgroclimaticoService.procesarBalanceYRiesgo(datosModulo1);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint POST para recibir datos reales dinámicos desde el Módulo 1
    @PostMapping("/evaluate")
    public ResponseEntity<ParcelStatusResponse> evaluateParcel(@RequestBody Modulo1InputDTO input) {
        ParcelStatusResponse resultado = motorAgroclimaticoService.procesarBalanceYRiesgo(input);
        return ResponseEntity.ok(resultado);
    }
}