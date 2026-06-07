package com.smartclinic.controller;

import com.smartclinic.service.DoctorService;
import com.smartclinic.security.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

```
private final DoctorService doctorService;
private final TokenService tokenService;

public DoctorController(DoctorService doctorService,
                        TokenService tokenService) {
    this.doctorService = doctorService;
    this.tokenService = tokenService;
}

@GetMapping
public Object getAllDoctors() {
    return doctorService.getAllDoctors();
}

@GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
public Object getDoctorAvailability(
        @PathVariable String user,
        @PathVariable Long doctorId,
        @PathVariable String date,
        @PathVariable String token) {

    if (!tokenService.validateToken(token)) {
        return "Invalid Token";
    }

    return doctorService.getDoctorAvailability(user, doctorId, date);
}
```

}
