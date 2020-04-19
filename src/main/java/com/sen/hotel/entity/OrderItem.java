package com.sen.hotel.entity;

public class OrderItem {
    private Integer iId;
    private String oId;
    private Integer rId;

    public OrderItem() {
    }

    public OrderItem(Integer iId, String oId, Integer rId) {
        this.iId = iId;
        this.oId = oId;
        this.rId = rId;
    }

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "iId=" + iId +
                ", oId='" + oId + '\'' +
                ", rId=" + rId +
                '}';
    }
}
