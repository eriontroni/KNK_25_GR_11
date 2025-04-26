package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationHistory {
    private int id;
    private int reservationId;
    private int customerId;
    private LocalDateTime changeDate;
    private String oldStatus;
    private String newStatus;

    private ReservationHistory(int id, int reservationId, int customerId, LocalDateTime changeDate, String oldStatus, String newStatus) {
        this.id = id;
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.changeDate = changeDate;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public static ReservationHistory getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int reservationId = resultSet.getInt("reservation_id");
        int customerId = resultSet.getInt("customer_id");

        String changeDateString = resultSet.getString("change_date");
        LocalDateTime changeDate = LocalDateTime.parse(changeDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String oldStatus = resultSet.getString("old_status");
        String newStatus = resultSet.getString("new_status");

        return new ReservationHistory(id, reservationId, customerId, changeDate, oldStatus, newStatus);
    }

    public int getId() {
        return id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
