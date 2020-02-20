/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * 
 */
public class user {

    String roomno;
    String from;
    String to;
    String slot;
    String status;
    String capacity;

    user(String roomno, String capacity, String slot, String status, String date, String to) {
        super();
        this.roomno = roomno;
        this.from = date;
        this.to = to;
        this.slot = slot;
        this.capacity = capacity;
        this.status = status;
    }
 user(String roomno, String capacity, String slot, String status) {
        super();
        this.roomno = roomno;
       // this.from = date;
       // this.to = to;
        this.slot = slot;
        this.capacity = capacity;
        this.status = status;
    }
    

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String name) {
        roomno = name;
    }

    public String getDate1() {
        return from;
    }

    public void setDate1(String date1) {
        from = date1;
    }

    public String getDate2() {
        return to;
    }

    public void setDate2(String date2) {
        to = date2;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String username) {
        slot = username;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setCapacity(String x) {
        capacity = x;
    }

    public void setStatus(String a) {
        status = a;
    }
}
