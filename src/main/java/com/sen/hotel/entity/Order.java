package com.sen.hotel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Order {
    private String oId;
    private Double totalPrice;
    private int totalNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date oTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    private String uname;
    private String phone;
    private String idcardnum;
    private Integer id;  //用户ID
    private List<Integer> rTds;

    private User user;

    public Order() {
    }

    public Order(String oId, Double totalPrice, int totalNum, Date oTime, Date inDate, Date outDate, String uname, String phone, String idcardnum, Integer id, List<Integer> rTds, User user) {
        this.oId = oId;
        this.totalPrice = totalPrice;
        this.totalNum = totalNum;
        this.oTime = oTime;
        this.inDate = inDate;
        this.outDate = outDate;
        this.uname = uname;
        this.phone = phone;
        this.idcardnum = idcardnum;
        this.id = id;
        this.rTds = rTds;
        this.user = user;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public Date getoTime() {
        return oTime;
    }

    public void setoTime(Date oTime) {
        this.oTime = oTime;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcardnum() {
        return idcardnum;
    }

    public void setIdcardnum(String idcardnum) {
        this.idcardnum = idcardnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getrTds() {
        return rTds;
    }

    public void setrTds(List<Integer> rTds) {
        this.rTds = rTds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oId='" + oId + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalNum=" + totalNum +
                ", oTime=" + oTime +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", idcardnum='" + idcardnum + '\'' +
                ", id=" + id +
                ", rTds=" + rTds +
                ", user=" + user +
                '}';
    }
}
