package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private UserDto user;
    private RoomDto room;
    private BookingDto booking;
    private List<UserDto> userList;
    private List<RoomDto> roomList;
    private List<BookingDto> bookingList;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getBookingConfirmationCode() {
		return bookingConfirmationCode;
	}
	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public RoomDto getRoom() {
		return room;
	}
	public void setRoom(RoomDto room) {
		this.room = room;
	}
	public BookingDto getBooking() {
		return booking;
	}
	public void setBooking(BookingDto booking) {
		this.booking = booking;
	}
	public List<UserDto> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}
	public List<RoomDto> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomDto> roomList) {
		this.roomList = roomList;
	}
	public List<BookingDto> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<BookingDto> bookingList) {
		this.bookingList = bookingList;
	}
	public Response(int statusCode, String message, String token, String role, String expirationTime,
			String bookingConfirmationCode, UserDto user, RoomDto room, BookingDto booking, List<UserDto> userList,
			List<RoomDto> roomList, List<BookingDto> bookingList) {
		this.statusCode = statusCode;
		this.message = message;
		this.token = token;
		this.role = role;
		this.expirationTime = expirationTime;
		this.bookingConfirmationCode = bookingConfirmationCode;
		this.user = user;
		this.room = room;
		this.booking = booking;
		this.userList = userList;
		this.roomList = roomList;
		this.bookingList = bookingList;
	}
	public Response() {
	
	}
    
    

}