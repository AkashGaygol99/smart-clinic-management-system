package com.smartclinic.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @PostMapping
    public String addPrescription() {
        return "Prescription added";
    }
}
