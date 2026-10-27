
package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private final RoomService roomService;

    // Constructor injection
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/count")
    public long getRoomCount() {
        return roomService.getRoomCount();
    }

    @GetMapping("/count/{roomType}")
    public long getRoomCountByType(@PathVariable String roomType) {
        return roomService.getRoomCountByType(roomType);
    }

    @GetMapping("/count/{roomType}/{status}")
    public long getRoomCountByTypeAndStatus(@PathVariable String roomType, @PathVariable String status){
        return roomService.getRoomCountByTypeAndStatus(roomType,status);
    }
}
