package com.kaua.booking_api.controller;

import com.kaua.booking_api.dto.booking.BookingRequestDTO;
import com.kaua.booking_api.dto.booking.BookingResponseDTO;
import com.kaua.booking_api.exeptions.BusinessException;
import com.kaua.booking_api.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO dto) throws BusinessException {
        BookingResponseDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable("id") Long id, @Valid @RequestBody BookingRequestDTO dto) throws BusinessException {
        BookingResponseDTO response = service.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable("id") Long id) throws BusinessException {
        BookingResponseDTO response = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<BookingResponseDTO> deleteBooking(@PathVariable("id") Long id) throws BusinessException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
