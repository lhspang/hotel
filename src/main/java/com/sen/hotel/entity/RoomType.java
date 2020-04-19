package com.sen.hotel.entity;

import java.util.List;

public class RoomType {
    private int tId;
    private String tName;
    private String describe;
    private String picture;
    private Double price;

    private List<Room> roomList;

    public RoomType() {
    }

    public RoomType(int tId, String tName, String describe, String picture, Double price) {
        this.tId = tId;
        this.tName = tName;
        this.describe = describe;
        this.picture = picture;
        this.price = price;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", describe='" + describe + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                '}';
    }
}
