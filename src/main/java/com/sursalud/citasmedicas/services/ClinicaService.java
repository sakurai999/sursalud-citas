package com.sursalud.citasmedicas.services;

import com.sursalud.citasmedicas.models.Cita;
import com.sursalud.citasmedicas.models.Medico;
import com.sursalud.citasmedicas.models.Paciente;
import com.sursalud.citasmedicas.repositories.CitaRepository;
import com.sursalud.citasmedicas.repositories.MedicoRepository;
import com.sursalud.citasmedicas.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaRepository citaRepository;

    // --- Pacientes ---
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // --- Médicos ---
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // --- Citas ---
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Cita crearCita(Cita cita) {
        cita.setEstado("PROGRAMADA");
        return citaRepository.save(cita);
    }

    public boolean cancelarCita(Long id) {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado("CANCELADA");
            citaRepository.save(cita);
            return true;
        }
        return false;
    }
}