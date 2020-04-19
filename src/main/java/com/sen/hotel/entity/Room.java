package com.sen.hotel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Room {
    private int rId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private int tId;

    private RoomType type;

    public Room() {
    }

    public Room(int rId, Date endTime, int tId, RoomType type) {
        this.rId = rId;
        this.endTime = endTime;
        this.tId = tId;
        this.type = type;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rId=" + rId +
                ", endTime=" + endTime +
                ", tId=" + tId +
                ", type=" + type +
                '}';
    }
}
