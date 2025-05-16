package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDiscount {
    private int id;
    private int reservation_id;
    private int discount_id;

    private ReservationDiscount(int id, int reservation_id, int discount_id) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.discount_id = discount_id;
    }

    public static ReservationDiscount getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int reservation_id = resultSet.getInt("reservation_id");
        int discount_id = resultSet.getInt("discount_id");

        return new ReservationDiscount(id, reservation_id, discount_id);
    }

    public int getId() {
        return id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }
}
