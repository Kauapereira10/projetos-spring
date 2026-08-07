package com.kaua.booking_api.controller;

import com.kaua.booking_api.dto.service.ServiceRequestDTO;
import com.kaua.booking_api.dto.service.ServiceResponseDTO;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createService(@Valid @RequestBody ServiceRequestDTO dto) throws BusinessException {
        ServiceResponseDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable("id") Long id, @Valid @RequestBody ServiceRequestDTO dto) throws BusinessException {
        ServiceResponseDTO response = service.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getService(@PathVariable("id") Long id) throws BusinessException {
        ServiceResponseDTO response = service.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") Long id) throws BusinessException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
