package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.BookingDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import com.example.demo.model.User;

public class Utils {
	 private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final SecureRandom secureRandom = new SecureRandom();


	    public static String generateRandomConfirmationCode(int length) {
	        StringBuilder stringBuilder = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
	            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
	            stringBuilder.append(randomChar);
	        }
	        return stringBuilder.toString();
	    }


	    public static UserDto mapUserEntityToUserDTO(User user) {
	        UserDto userDTO = new UserDto();

	        userDTO.setId(user.getId());
	        userDTO.setName(user.getName());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setPhoneNumber(user.getPhoneNumber());
	        userDTO.setRole(user.getRole());
	        return userDTO;
	    }

	    public static RoomDto mapRoomEntityToRoomDto(Room room) {
	        RoomDto RoomDto = new RoomDto();

	        RoomDto.setId(room.getId());
	        RoomDto.setRoomType(room.getRoomType());
	        RoomDto.setRoomPrice(room.getRoomPrice());
	        RoomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
	        RoomDto.setRoomDescription(room.getRoomDescription());
	        return RoomDto;
	    }

	    public static BookingDto mapBookingEntityToBookingDto(Booking booking) {
	        BookingDto BookingDto = new BookingDto();
	        // Map simple fields
	        BookingDto.setId(booking.getId());
	        BookingDto.setCheckInDate(booking.getCheckInDate());
	        BookingDto.setCheckOutDate(booking.getCheckOutDate());
	        BookingDto.setNumOfAdults(booking.getNumOfAdults());
	        BookingDto.setNumOfChildren(booking.getNumOfChildren());
	        BookingDto.setTotalNumOfGuest(booking.getTotalNumOfGuest());
	        BookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());
	        return BookingDto;
	    }

	    public static RoomDto mapRoomEntityToRoomDtoPlusBookings(Room room) {
	        RoomDto RoomDto = new RoomDto();

	        RoomDto.setId(room.getId());
	        RoomDto.setRoomType(room.getRoomType());
	        RoomDto.setRoomPrice(room.getRoomPrice());
	        RoomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
	        RoomDto.setRoomDescription(room.getRoomDescription());

	        if (room.getBookings() != null) {
	            RoomDto.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingDto).collect(Collectors.toList()));
	        }
	        return RoomDto;
	    }

	    public static BookingDto mapBookingEntityToBookingDtoPlusBookedRooms(Booking booking, boolean mapUser) {

	        BookingDto BookingDto = new BookingDto();
	        // Map simple fields
	        BookingDto.setId(booking.getId());
	        BookingDto.setCheckInDate(booking.getCheckInDate());
	        BookingDto.setCheckOutDate(booking.getCheckOutDate());
	        BookingDto.setNumOfAdults(booking.getNumOfAdults());
	        BookingDto.setNumOfChildren(booking.getNumOfChildren());
	        BookingDto.setTotalNumOfGuest(booking.getTotalNumOfGuest());
	        BookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());
	        if (mapUser) {
	            BookingDto.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
	        }
	        if (booking.getRoom() != null) {
	            RoomDto RoomDto = new RoomDto();

	            RoomDto.setId(booking.getRoom().getId());
	            RoomDto.setRoomType(booking.getRoom().getRoomType());
	            RoomDto.setRoomPrice(booking.getRoom().getRoomPrice());
	            RoomDto.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
	            RoomDto.setRoomDescription(booking.getRoom().getRoomDescription());
	            BookingDto.setRoom(RoomDto);
	        }
	        return BookingDto;
	    }

	    public static UserDto mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user) {
	        UserDto userDTO = new UserDto();

	     
	        userDTO.setName(user.getName());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setPhoneNumber(user.getPhoneNumber());
	        userDTO.setRole(user.getRole());

	        if (!user.getBookings().isEmpty()) {
	            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDtoPlusBookedRooms(booking, false)).collect(Collectors.toList()));
	        }
	        return userDTO;
	    }


	    public static List<UserDto> mapUserListEntityToUserListDTO(List<User> userList) {
	        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
	    }

	    public static List<RoomDto> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
	        return roomList.stream().map(Utils::mapRoomEntityToRoomDto).collect(Collectors.toList());
	    }

	    public static List<BookingDto> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
	        return bookingList.stream().map(Utils::mapBookingEntityToBookingDto).collect(Collectors.toList());
	    }

}
