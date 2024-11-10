package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	   Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
	   List<Booking> findByUserId(Long userId);
	   List<Booking> findByRoomId(Long id);
}
