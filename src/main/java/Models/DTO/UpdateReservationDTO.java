package Models.DTO;

import java.sql.Date;

public class UpdateReservationDTO {
    private int id;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalPrice;

    public UpdateReservationDTO(int id, Date checkInDate, Date checkOutDate, String status, double totalPrice) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
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
}
