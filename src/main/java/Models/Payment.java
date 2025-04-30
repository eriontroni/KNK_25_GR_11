package Models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {
    private int id;
    private int reservationId;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;

    private Payment(int id, int reservationId, BigDecimal amount, String paymentMethod, String paymentStatus) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public static Payment getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int reservationId = rs.getInt("reservation_id");
        BigDecimal amount = rs.getBigDecimal("amount");
        String paymentMethod = rs.getString("payment_method");
        String paymentStatus = rs.getString("payment_status");

        return new Payment(id, reservationId, amount, paymentMethod, paymentStatus);
    }

    public int getId() { return id; }

    public int getReservationId() { return reservationId; }

    public BigDecimal getAmount() { return amount; }

    public String getPaymentMethod() { return paymentMethod;}

    public String getPaymentStatus() { return paymentStatus; }
}
