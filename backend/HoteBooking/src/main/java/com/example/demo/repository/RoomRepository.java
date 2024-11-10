package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	@Query("Select distinct r.roomType from Room r ")
	List<String> findDistinctRoomType();
	@Query("select r from Room r where r.id not in (select b.room.id from Booking b )")
	List<Room> getAllAvailableRoom();
	
	@Query("select r from Room r where r.roomType like %:roomType%  and r.id not in "
			+ "(select b.room.id from Booking b where (b.checkInDate <= :checkOutDate and b.checkOutDate >=:checkInDate))")
	List<Room> findAvailableRoomByTypeAndDate(LocalDate checkInDate, LocalDate checkOutDate, String roomType );
	
}
