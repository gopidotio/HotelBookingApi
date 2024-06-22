package io.gopi.capgemini.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import io.gopi.capgemini.Resource.vm.BookingRequestDTO;

public class Booking implements Serializable {
    
    private String id;
    private int roomNo;
    private String guestName;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Booking() {
        // empty constructor
    }

    public Booking(BookingRequestDTO request) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.roomNo = request.getRoomNo();
        this.guestName = request.getGuestName();
        this.checkIn = request.getCheckIn();
        this.checkOut = request.getCheckOut();
    }

    public Booking(int roomNo, String guestName, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.roomNo = roomNo;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
    
    public int getRoomNo() {
        return roomNo;
    }

    public String getGuestName() {
        return guestName;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", roomNo=" + roomNo + ", guestName=" + guestName + ", checkIn=" + checkIn
                + ", checkOut=" + checkOut + "]";
    }

    
}
