package io.gopi.capgemini;

import java.io.IOException;

import io.gopi.capgemini.Config.ApiServer;

public class SspApplication {
    
    public static void main(String[] args) throws IOException {
        ApiServer server = new ApiServer();
        server.start();
    }
}