
package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.RoomRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public Booking bookRoom(Booking booking) {

        System.out.println("Booking received: " + booking);
        System.out.println("Room ID received: " + booking.getRoomId());
        if (booking.getRoomId() == null ||
                booking.getRoomId().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Room ID is required"
            );
        }

        Room room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Room not found"
                        )
                );

        if ("BOOKED".equalsIgnoreCase(room.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Room is already booked"
            );
        }

        booking.setBookingStatus("CONFIRMED");
        Booking savedBooking = bookingRepository.save(booking);

        room.setStatus("BOOKED");
        roomRepository.save(room);

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
