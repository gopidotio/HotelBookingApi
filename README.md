# HotelBookingApi
To implement a simple hotel booking manager in Java, as a microservice API. The number of rooms should be configurable, and it should expose the following methods:

1. A method to store a booking. A booking consists of a guest name, a room number, and a
date.
2. A method to find the available rooms on a given date.
3. A method to find all the bookings for a given guest.

## Requirements
- Java Development Kit (JDK)
- Maven 
- Git

## Setup

1. To clone the repository to your local machine:
```sh
   git clone git@github.com:gopidotio/HotelBookingApi.git
```

2. Navigate to the project directory:
```sh
    cd HotelBookingApi
```

3. To build the project using Maven:
```sh
    mvn clean package
```

## Usage
To run the booking-api.jar file to start the HTTP server:
```sh
    java -jar target/booking-api.jar
```


### Save Booking:

#### API Endpoint

- **HTTP Method:** POST
- **Endpoint:** `localhost:8092/api/v1/rest/bookings`
- **Request Body (Form Data):** `guestName={guest_name}, roomNo={room_no}, checkIn={yyyy-mm-ddTh:mm:ss}, checkOut={yyyy-mm-ddTh:mm:ss}`
- **Sample CURL Request:**
  ```sh
  curl -v -XPOST -d "guestName=Gopi&roomNo=1&checkIn=2024-06-01T15:00:00&checkOut=2024-06-02T12:00:00" "localhost:8092/api/v1/rest/bookings"
  ```


### Get Bookings By Guest Name:

#### API Endpoint

- **HTTP Method:** GET
- **Endpoint:** `localhost:8092/api/v1/rest/bookings`
- **Request Params:** `guest={guest_name}`
- **Sample CURL Request:**
  ```sh
  curl -v 'localhost:8092/api/v1/rest/bookings?guest=gopi'
  ```


### Get Available Rooms By Dates:

#### API Endpoint

- **HTTP Method:** GET
- **Endpoint:** `localhost:8092/api/v1/rest/bookings`
- **Request Params:** `checkIn={yyyy-mm-ddTh:mm:ss}, checkOut={yyyy-mm-ddTh:mm:ss}`
- **Sample CURL Request:**
  ```sh
  curl -v 'localhost:8092/api/v1/rest/bookings?checkIn=2024-06-01T15:00:00&checkOut=2024-06-02T12:00:00'
  ```


## To run the test cases:
```sh
    mvn test
```