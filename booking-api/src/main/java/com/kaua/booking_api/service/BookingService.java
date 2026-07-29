package com.kaua.booking_api.service;

import com.kaua.booking_api.dto.booking.BookingRequestDTO;
import com.kaua.booking_api.dto.booking.BookingResponseDTO;
import com.kaua.booking_api.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }


}
