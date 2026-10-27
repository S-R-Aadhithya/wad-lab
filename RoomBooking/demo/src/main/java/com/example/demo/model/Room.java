package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="rooms")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private String roomId;
    private String roomType;
    private double price;
    private String status;
}
