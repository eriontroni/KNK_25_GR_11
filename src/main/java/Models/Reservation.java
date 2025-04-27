package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private int id;
    private int customerId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private double totalPrice;

    //konstruktor per me kriju nje rezervim
    public Reservation(int id, int customerId, int roomId, LocalDate checkInDate, LocalDate checkOutDate, String status, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // getter per fushat
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public String getFormattedCheckInDate() {
        return checkInDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedCheckOutDate() {
        return checkOutDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
