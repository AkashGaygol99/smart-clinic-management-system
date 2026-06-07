package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<String> getDoctorAvailability(Long doctorId, String date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return doctor.getAvailableTimes();
    }

    public boolean validateLogin(String email, String password) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);

        return doctor.isPresent()
                && doctor.get().getPassword().equals(password);
    }
}
