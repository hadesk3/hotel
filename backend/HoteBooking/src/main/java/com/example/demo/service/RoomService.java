package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Response;
import com.example.demo.dto.RoomDto;
import com.example.demo.exception.OurException;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.utils.Utils;

@Service
public class RoomService {
	 	@Autowired
	    private RoomRepository roomRepository;
	    @Autowired
	    private AwsService awsS3Service;
	    
	    
	    public Response addNewRoom(MultipartFile photo,String roomType, String description, BigDecimal price)
	    {
	    	Response response = new Response();
	    	try {
				String imgUrl = awsS3Service.saveImageToS3(photo);
				Room room = new Room();
				room.setRoomPhotoUrl(imgUrl);
	            room.setRoomType(roomType);
	            room.setRoomPrice(price);
	            room.setRoomDescription(description);
	            Room savedRoom = roomRepository.save(room);
	            RoomDto roomDto = Utils.mapRoomEntityToRoomDto(savedRoom);
	            
	            response.setStatusCode(200);
	            response.setRoom(roomDto);
	            response.setMessage("successful");
	            
			} 
	    	catch (Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error saving a room " + e.getMessage());
			}
	    	return response;
	    }
	    
	    public List<String> getAllRoomTypes() {
	        return roomRepository.findDistinctRoomType();
	    }
	    
	    public Response getAllRooms() {
	     Response response = new Response();

	        try {
	            List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	            List<RoomDto> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setRoomList(roomDTOList);

	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	    }
	    
	    public Response deleteRoom(Long roomId) {
	        Response response = new Response();

	        try {
	            roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
	            roomRepository.deleteById(roomId);
	            response.setStatusCode(200);
	            response.setMessage("successful");

	        } catch (OurException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());
	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	    }

	    public Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
	        Response response = new Response();

	        try {
	            String imageUrl = null;
	            if (photo != null && !photo.isEmpty()) {
	                imageUrl = awsS3Service.saveImageToS3(photo);
	            }
	            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
	            if (roomType != null) room.setRoomType(roomType);
	            if (roomPrice != null) room.setRoomPrice(roomPrice);
	            if (description != null) room.setRoomDescription(description);
	            if (imageUrl != null) room.setRoomPhotoUrl(imageUrl);

	            Room updatedRoom = roomRepository.save(room);
	            RoomDto roomDTO = Utils.mapRoomEntityToRoomDto(updatedRoom);

	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setRoom(roomDTO);

	        } catch (OurException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());
	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	        
	        
	    }
	    
	    public Response getRoomById(Long roomId) {
	        Response response = new Response();

	        try {
	            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
	            RoomDto roomDTO = Utils.mapRoomEntityToRoomDtoPlusBookings(room);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setRoom(roomDTO);

	        } catch (OurException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());
	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	    }
	    
	    public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
	        Response response = new Response();

	        try {
	            List<Room> availableRooms = roomRepository.findAvailableRoomByTypeAndDate(checkInDate, checkOutDate, roomType);
	            List<RoomDto> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(availableRooms);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setRoomList(roomDTOList);

	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	    
	    
	    }
	    
	    
	    public Response getAllAvailableRooms() {
	        Response response = new Response();

	        try {
	            List<Room> roomList = roomRepository.getAllAvailableRoom();
	            List<RoomDto> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
	            response.setStatusCode(200);
	            response.setMessage("successful");
	            response.setRoomList(roomDTOList);

	        } catch (OurException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());
	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error saving a room " + e.getMessage());
	        }
	        return response;
	    }
	    
	    
}
