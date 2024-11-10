package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
	   private Long id;
	    private String roomType;
	    private BigDecimal roomPrice;
	    private String roomPhotoUrl;
	    private String roomDescription;
	    private List<BookingDto> bookings;
		public RoomDto(Long id, String roomType, BigDecimal roomPrice, String roomPhotoUrl, String roomDescription,
				List<BookingDto> bookings) {
		
			this.id = id;
			this.roomType = roomType;
			this.roomPrice = roomPrice;
			this.roomPhotoUrl = roomPhotoUrl;
			this.roomDescription = roomDescription;
			this.bookings = bookings;
		}
	    
	    public RoomDto()
	    {
	    	
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRoomType() {
			return roomType;
		}

		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}

		public BigDecimal getRoomPrice() {
			return roomPrice;
		}

		public void setRoomPrice(BigDecimal roomPrice) {
			this.roomPrice = roomPrice;
		}

		public String getRoomPhotoUrl() {
			return roomPhotoUrl;
		}

		public void setRoomPhotoUrl(String roomPhotoUrl) {
			this.roomPhotoUrl = roomPhotoUrl;
		}

		public String getRoomDescription() {
			return roomDescription;
		}

		public void setRoomDescription(String roomDescription) {
			this.roomDescription = roomDescription;
		}

		public List<BookingDto> getBookings() {
			return bookings;
		}

		public void setBookings(List<BookingDto> bookings) {
			this.bookings = bookings;
		}
	    
}
