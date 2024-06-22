package io.gopi.capgemini.Resource;

import io.gopi.capgemini.Config.Constants;
import io.gopi.capgemini.Model.Booking;
import io.gopi.capgemini.Resource.vm.BookingRequestDTO;
import io.gopi.capgemini.Service.BookingService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BookingResource implements HttpHandler {
    
    private final BookingService bookingService;

    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String response = "";
        int statusCode = 200;

        try {
            if (Constants.POST.equalsIgnoreCase(method)) {
            
                Map<String, String> params = Constants.parseQueryParams(Constants.readInputStream(exchange.getRequestBody()));
                String bookingId = bookingService.save(new BookingRequestDTO(params));
                response = String.format("Booking stored successfully with ID: %s", bookingId);

            } else if (Constants.GET.equalsIgnoreCase(method)) {
                Map<String, String> params = Constants.parseQueryParams(exchange.getRequestURI().getQuery());

                if (params.containsKey(Constants.GUEST)) {
                    
                    List<Booking> bookings = bookingService.fetchBookingsByGuest(params.get(Constants.GUEST));
                    response = bookings.stream().map(Object::toString).collect(Collectors.joining(", "));
                } else if(params.containsKey(Constants.CHECK_IN) && params.containsKey(Constants.CHECK_OUT)) {
                    
                    LocalDateTime checkIn = LocalDateTime.parse(params.get(Constants.CHECK_IN), DateTimeFormatter.ISO_DATE_TIME);
                    LocalDateTime checkOut = LocalDateTime.parse(params.get(Constants.CHECK_OUT), DateTimeFormatter.ISO_DATE_TIME);

                    List<Integer> availableRooms = bookingService.fetchAvailableRooms(checkIn, checkOut);
                    response = availableRooms.toString();
                } else {
                    response = "Unsupported method";
                    statusCode = 405;
                }
            }

        } catch (DateTimeParseException | NumberFormatException e) {
            response = "Invalid input format";
            statusCode = 400;
        } catch (Exception e) {
            response = "Internal server error";
            statusCode = 500;
        }
        
        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

}
