package io.gopi.capgemini.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import io.gopi.capgemini.Model.Booking;
import io.gopi.capgemini.Resource.vm.BookingRequestDTO;

public class BookingService {

    // List of room numbers available in the hotel
    private final List<Integer> rooms;

    // Thread-safe map to store bookings for each room
    private final Map<Integer, List<Booking>> bookingsByRoom = new ConcurrentHashMap<>();

    // Constructor to initialize the service with a specific number of rooms
    public BookingService(int numberOfRooms) {
        // Initialize the list of rooms
        this.rooms = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(i);
        }
    }

    /**
     * Finds available rooms for a given date range.
     *
     * @param checkIn  the check-in time
     * @param checkOut the check-out time
     * @return a list of available room numbers
     */
    public List<Integer> fetchAvailableRooms(LocalDateTime checkIn, LocalDateTime checkOut) {

        // Find rooms that are booked during the given date range
        Set<Integer> bookedRooms = bookingsByRoom.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(booking -> !(checkOut.isBefore(booking.getCheckIn())
                                || checkIn.isAfter(booking.getCheckOut()))))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        // Return the list of rooms that are not booked during the given date range
        return rooms.stream()
                .filter(roomNumber -> !bookedRooms.contains(roomNumber))
                .collect(Collectors.toList());
    }

    /**
     * Finds all bookings for a given guest.
     *
     * @param guest the name of the guest
     * @return a list of bookings for the specified guest
     */
    public List<Booking> fetchBookingsByGuest(String guest) {
        // Collect and return all bookings that match the given guest name
        return bookingsByRoom.values().stream()
                .flatMap(Collection::stream)
                .filter(booking -> booking.getGuestName().equalsIgnoreCase(guest))
                .collect(Collectors.toList());
    }

    /**
     * Stores a booking for a given guest in a specified room with check-in and
     * check-out times.
     *
     * @requestBody BookingRequestDTO request
     * @return the unique ID of the created booking
     */
    public String save(BookingRequestDTO request) {

        // Create a new Booking object
        Booking booking = new Booking(request);

        // Store the booking in a thread-safe manner
        bookingsByRoom.computeIfAbsent(request.getRoomNo(), room -> new CopyOnWriteArrayList<>()).add(booking);
        return booking.getId();
    }
}
