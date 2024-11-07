import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Room> rooms = Arrays.asList(
                new Room("RS001", RoomType.SINGLE, 8),
                new Room("RD001", RoomType.DOUBLE, 12),
                new Room("RQ002", RoomType.QUEEN, 35),
                new Room("RT001", RoomType.TRIPLE, 12.5),
                new Room("RQ001", RoomType.QUAD, 20.5)
        );


        List<Customer> customers = new ArrayList<>(Arrays.asList(
                new Customer("001", "Mr.Linus Tovaldo", "84125325346457"),
                new Customer("002", "Mr.Bill", "91124235346467"),
                new Customer("003", "Mr.Turing", "911423534646")
        ));


        List<Booking> bookings = new ArrayList<>(Arrays.asList(
                new Booking(1, "RS001", "001", LocalDateTime.parse("2023-03-15T09:30:15"), LocalDateTime.parse("2023-03-16T12:30:45")),
                new Booking(2, "RS001", "002", LocalDateTime.parse("2023-06-09T19:30:25"), LocalDateTime.parse("2023-06-10T11:25:15")),
                new Booking(3, "RD001", "002", LocalDateTime.parse("2023-03-11T10:10:05"), LocalDateTime.parse("2023-03-13T11:05:10")),
                new Booking(4, "RT001", "003", LocalDateTime.parse("2023-11-11T11:11:15"), LocalDateTime.parse("2023-11-13T11:15:15")),
                new Booking(5, "RT001", "001", LocalDateTime.parse("2023-10-25T09:20:25"), LocalDateTime.parse("2023-10-26T12:25:30")),
                new Booking(6, "RQ001", "001", LocalDateTime.parse("2023-08-18T18:25:35"), LocalDateTime.parse("2023-08-19T11:35:20"))
        ));

        BookingService bookingService = new BookingService(rooms, customers, bookings);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. Create new booking");
            System.out.println("2. View revenue by room type");
            System.out.println("3. View room type with highest revenue in 2023");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    bookingService.createBooking();
                    break;
                case 2:
                    System.out.println("Revenue by Room Type:");
                    Map<RoomType, Double> revenueByRoomType = bookingService.calculateRevenueByRoomType();
                    revenueByRoomType.forEach((type, revenue) -> System.out.println(type + ": $" + revenue));
                    break;
                case 3:
                    System.out.println("Room Type with Highest Revenue in 2023:");
                    System.out.println(bookingService.getHighestRevenueRoomTypeIn2023().orElse(null));
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
