package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return List.of(); // Mock implementation
    }
}
