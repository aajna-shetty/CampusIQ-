package com.campus.Assistant.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "locations")
public class Location {
    @Id
    private String id;
    private String name;

    public Location() {
    }

    public Location(String id, String name, String block, String floor, String roomNumber) {
        this.id = id;
        this.name = name;
        this.block = block;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    private String block;
    private String floor;
    private String roomNumber;
}
