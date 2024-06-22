package io.gopi.capgemini.Resource.vm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import io.gopi.capgemini.Config.Constants;

public class BookingRequestDTO implements Serializable {
    
    private int roomNo;
    private String guestName;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public BookingRequestDTO() {
        // empty constructor
    }

    public BookingRequestDTO(int roomNo, String guestName, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.roomNo = roomNo;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public BookingRequestDTO(Map<String, String> params) {
        this.roomNo = Integer.parseInt(params.get(Constants.ROOM_NO));
        this.guestName = params.get(Constants.GUEST_NAME);
        this.checkIn = LocalDateTime.parse(params.get(Constants.CHECK_IN), DateTimeFormatter.ISO_DATE_TIME);
        this.checkOut = LocalDateTime.parse(params.get(Constants.CHECK_OUT), DateTimeFormatter.ISO_DATE_TIME);
    }

    public int getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public LocalDateTime getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }
    public LocalDateTime getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
