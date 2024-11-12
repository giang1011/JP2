package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings;
    private Scanner scanner = new Scanner(System.in);

    public BookingService(List<Room> rooms, List<Customer> customers, List<Booking> bookings) {
        this.rooms = rooms;
        this.customers = customers;
        this.bookings = bookings;
    }

    public void createBooking() {
        System.out.println("Enter Customer ID:");
        String customerId = scanner.nextLine();

        System.out.println("Enter Customer Name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter Customer Phone:");
        String customerPhone = scanner.nextLine();

        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElseGet(() -> {
                    Customer newCustomer = new Customer(customerId, customerName, customerPhone);
                    customers.add(newCustomer);
                    return newCustomer;
                });

        System.out.println("Available Rooms:");
        rooms.forEach(room -> System.out.println(room.getId() + " - " + room.getRoomType() + " - $" + room.getPricePerHour() + "/hour"));

        System.out.println("Enter Room ID:");
        String roomId = scanner.nextLine();

        System.out.println("Enter Check-in Date and Time (yyyy-MM-dd HH:mm):");
        LocalDateTime checkIn = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("Enter Check-out Date and Time (yyyy-MM-dd HH:mm):");
        LocalDateTime checkOut = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


        boolean isRoomAvailable = bookings.stream()
                .filter(booking -> booking.getRoomId().equals(roomId))
                .noneMatch(booking ->
                        (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn())));

        if (!isRoomAvailable) {
            System.out.println("Room is not available for the selected time period. Please try a different room or time.");
            return;
        }


        System.out.println("\nPlease confirm the booking details:");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Phone: " + customer.getPhone());
        System.out.println("Room ID: " + roomId);
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);

        System.out.println("Do you confirm this booking? (yes/no)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            int bookingId = bookings.size() + 1;
            bookings.add(new Booking(bookingId, roomId, customer.getId(), checkIn, checkOut));
            System.out.println("Booking confirmed!");
        } else {
            System.out.println("Booking cancelled.");
        }
    }

    public List<Booking> findBookingsByCustomer(String cusName, String cusPhone) {
        return bookings.stream()
                .filter(booking -> {
                    Customer customer = customers.stream()
                            .filter(c -> c.getId().equals(booking.getCustomerId()))
                            .findFirst().orElse(null);
                    return customer != null && customer.getName().equalsIgnoreCase(cusName) && customer.getPhone().equals(cusPhone);
                })
                .collect(Collectors.toList());
    }

    public Map<RoomType, Double> calculateRevenueByRoomType() {
        Map<String, Room> roomMap = rooms.stream().collect(Collectors.toMap(Room::getId, room -> room));
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> roomMap.get(booking.getRoomId()).getRoomType(),
                        Collectors.summingDouble(booking -> {
                            Room room = roomMap.get(booking.getRoomId());
                            long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                            return room.getPricePerHour() * hours;
                        })
                ));
    }

    public Optional<RoomType> getHighestRevenueRoomTypeIn2023() {
        Map<String, Room> roomMap = rooms.stream().collect(Collectors.toMap(Room::getId, room -> room));
        return bookings.stream()
                .filter(booking -> booking.getCheckIn().getYear() == 2023)
                .collect(Collectors.groupingBy(
                        booking -> roomMap.get(booking.getRoomId()).getRoomType(),
                        Collectors.summingDouble(booking -> {
                            Room room = roomMap.get(booking.getRoomId());
                            long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                            return room.getPricePerHour() * hours;
                        })
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
