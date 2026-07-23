package com.kaua.booking_api.repository;

import com.kaua.booking_api.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
