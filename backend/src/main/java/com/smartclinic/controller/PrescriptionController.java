package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

```
private final TokenService tokenService;

public PrescriptionController(TokenService tokenService) {
    this.tokenService = tokenService;
}

@PostMapping("/{token}")
public ResponseEntity<?> addPrescription(
        @PathVariable String token,
        @Valid @RequestBody Prescription prescription) {

    if (!tokenService.validateToken(token)) {
        return ResponseEntity.badRequest()
                .body("Invalid token");
    }

    Map<String, Object> response = new HashMap<>();
    response.put("message", "Prescription added successfully");
    response.put("prescription", prescription);

    return ResponseEntity.ok(response);
}
```

}
