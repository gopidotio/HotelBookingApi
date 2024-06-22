package io.gopi.capgemini;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.gopi.capgemini.Model.Booking;
import io.gopi.capgemini.Resource.vm.BookingRequestDTO;
import io.gopi.capgemini.Service.BookingService;


public class BookingServiceTest {
    private BookingService bookingService;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String guest;

    @Before
    public void startup() {
        this.bookingService = new BookingService(5);
        this.checkIn = LocalDateTime.of(2024, 6, 20, 15, 0);
        this.checkOut = LocalDateTime.of(2024, 6, 24, 12, 0);
        this.guest = "Gopi";
    }

    @Test
    public void testSaveBooking() {
        bookingService.save(new BookingRequestDTO(2, guest, checkIn, checkOut));
        List<Booking> bookings = bookingService.fetchBookingsByGuest(guest);

        assertEquals(1, bookings.size());
        assertEquals(guest, bookings.get(0).getGuestName());
        assertEquals(2, bookings.get(0).getRoomNo());
        assertEquals(checkIn, bookings.get(0).getCheckIn());
        assertEquals(checkOut, bookings.get(0).getCheckOut());
    }

    @Test
    public void testFetchAvailableRooms() {
        bookingService.save(new BookingRequestDTO(1, guest, checkIn, checkOut));
        List<Integer> rooms = bookingService.fetchAvailableRooms(checkIn, checkOut);

        assertEquals(4, rooms.size());
        assertEquals(Arrays.asList(2, 3, 4, 5), rooms);
    }

    @Test
    public void testFetchBookingByGuest() {

        bookingService.save(new BookingRequestDTO(2, guest, checkIn, checkOut));
        bookingService.save(new BookingRequestDTO(4, guest, checkIn.plusDays(4), checkOut.plusDays(4)));

        List<Booking> bookings = bookingService.fetchBookingsByGuest(guest);
        assertEquals(2, bookings.size());
    }
}
