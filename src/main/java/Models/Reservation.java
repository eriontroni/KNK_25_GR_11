package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {
    private int id;
    private int customerId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalPrice;

    public Reservation(int id, int customerId, int roomId, Date checkInDate, Date checkOutDate, String status, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public static Reservation getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int customerId = resultSet.getInt("customer_id");
        int roomId = resultSet.getInt("room_id");
        Date checkInDate = resultSet.getDate("check_in_date");
        Date checkOutDate = resultSet.getDate("check_out_date");
        String status = resultSet.getString("status");
        double totalPrice = resultSet.getDouble("total_price");

        return new Reservation(id, customerId, roomId, checkInDate, checkOutDate, status, totalPrice);
    }

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
}
