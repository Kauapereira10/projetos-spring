package com.kaua.booking_api.controller;

import com.kaua.booking_api.dto.user.UserRequestDTO;
import com.kaua.booking_api.dto.user.UserResponseDTO;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO dto) throws BusinessException {
        UserResponseDTO response = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserRequestDTO dto) throws BusinessException {
        UserResponseDTO response = service.updateUser(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) throws BusinessException {
        UserResponseDTO response = service.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws BusinessException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}/type")
    public ResponseEntity<UserResponseDTO> updateUserType(@PathVariable("id") Long id, @Valid @RequestBody UserRequestDTO dto) throws BusinessException {
        UserResponseDTO response = service.updateUserType(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
