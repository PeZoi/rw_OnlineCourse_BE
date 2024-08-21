package com.example.backend_rw.controller;

import com.example.backend_rw.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Integer certificateId){
        return ResponseEntity.ok(certificateService.getById(certificateId));
    }
}