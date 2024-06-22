package io.gopi.capgemini.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class Constants {
    public static final String GET = "GET";
    public static final String POST = "POST";

    public static final String GUEST = "guest";

    public static final String ROOM_NO = "roomNo";
    public static final String GUEST_NAME = "guestName";
    public static final String CHECK_IN = "checkIn";
    public static final String CHECK_OUT = "checkOut";


    public static Map<String, String> parseQueryParams(String query) {
        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(
                        param -> param[0], 
                        param -> param.length > 1 ? param[1] : ""));
    }

    public static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
