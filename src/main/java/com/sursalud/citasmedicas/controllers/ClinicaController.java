package com.sursalud.citasmedicas.controllers;

import com.sursalud.citasmedicas.models.Cita;
import com.sursalud.citasmedicas.models.Medico;
import com.sursalud.citasmedicas.models.Paciente;
import com.sursalud.citasmedicas.services.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @GetMapping("/")
    public String inicio() {
        return "Bienvenido al sistema Sur Salud - API Citas Médicas activa y funcionando correctamente.";
    }

    // --- Endpoints de Pacientes ---
    @GetMapping("/api/pacientes")
    public List<Paciente> consultarPacientes() {
        return clinicaService.listarPacientes();
    }

    @PostMapping("/api/pacientes")
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return clinicaService.guardarPaciente(paciente);
    }

    // --- Endpoints de Médicos ---
    @GetMapping("/api/medicos")
    public List<Medico> consultarMedicos() {
        return clinicaService.listarMedicos();
    }

    @PostMapping("/api/medicos")
    public Medico crearMedico(@RequestBody Medico medico) {
        return clinicaService.guardarMedico(medico);
    }

    // --- Endpoints de Citas ---
    @GetMapping("/api/citas")
    public List<Cita> consultarCitas() {
        return clinicaService.listarCitas();
    }

    @PostMapping("/api/citas")
    public Cita crearCita(@RequestBody Cita cita) {
        return clinicaService.crearCita(cita);
    }

    @DeleteMapping("/api/citas/{id}")
    public ResponseEntity<String> cancelarCita(@PathVariable Long id) {
        boolean cancelada = clinicaService.cancelarCita(id);
        if (cancelada) {
            return ResponseEntity.ok("Cita cancelada con éxito");
        }
        return ResponseEntity.notFound().build();
    }
}