package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        room.setStatus("AVAILABLE");
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public long getRoomCount() {
        return roomRepository.count();
    }

    public long getRoomCountByType(String roomType) {
        return roomRepository.countByRoomType(roomType);
    }

    public long getRoomCountByTypeAndStatus(String roomType, String status){
        return roomRepository.countByRoomTypeAndStatus(roomType, status);
    }
}
