package Entity;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private String roomId;
    private String customerId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Booking(int id, String roomId, String customerId, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = id;
        this.roomId = roomId;
        this.customerId = customerId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}

