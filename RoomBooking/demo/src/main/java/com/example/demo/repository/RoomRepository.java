package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    long countByRoomType(String roomType);
    long countByRoomTypeAndStatus(String roomType, String status);
}
