package com.smartclinic.controller;

import com.smartclinic.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public Object getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
