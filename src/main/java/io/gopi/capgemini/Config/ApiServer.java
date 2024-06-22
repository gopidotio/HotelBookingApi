package io.gopi.capgemini.Config;

import io.gopi.capgemini.Resource.BookingResource;
import io.gopi.capgemini.Service.BookingService;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class ApiServer {
    
    public void start() throws IOException {

        // Configurable number of rooms
        BookingService bookingService = new BookingService(5);
        HttpServer server = HttpServer.create(new InetSocketAddress(8092), 0);
        server.createContext("/api/v1/rest/bookings", new BookingResource(bookingService));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8092");
    }
}
