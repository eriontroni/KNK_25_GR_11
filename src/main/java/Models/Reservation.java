package Models;

import java.sql.Date;

public class Reservation {
    private int id;
    private int customerId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalPrice;

    //konstruktor per me kriju nje rezervim
    public Reservation(int id, int customerId, int roomId, Date checkInDate, Date checkOutDate, String status, double totalPrice) {
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    // formatimi i dates se hyrjes si string
    public String getFormattedCheckInDate() {
        return checkInDate.toString();  // "yyyy-mm-dd"
    }

    // data e daljes si string
    public String getFormattedCheckOutDate() {
        return checkOutDate.toString();
    }

    // kalimi i java.sql.Date ne LocalDate (nese esht e nevojshme per ndonje rast tjeter)
    public java.time.LocalDate getLocalCheckInDate() {
        return checkInDate.toLocalDate();
    }

    public java.time.LocalDate getLocalCheckOutDate() {
        return checkOutDate.toLocalDate();
    }

}
