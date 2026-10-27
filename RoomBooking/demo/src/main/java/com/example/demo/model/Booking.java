package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@Document(collection="bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String bookingID;
    private String customerName;
    private String phone;
    private String roomId;
    private String bookingStatus;
}
