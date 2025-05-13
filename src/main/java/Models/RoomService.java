package Models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class RoomService {
    private int id;
    private int reservation_id;
    private String service_name;
    private BigDecimal price;
    private Date requested_at;

    public RoomService(int id, int reservation_id, String service_name, BigDecimal price, Date requested_at){
        this.id=id;
        this.reservation_id=reservation_id;
        this.service_name=service_name;
        this.price=price;
        this.requested_at=requested_at;
    }

    public static RoomService getInstance(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        int reservation_id=resultSet.getInt("reservation_id");
        String service_name=resultSet.getString("service_name");
        BigDecimal price=resultSet.getBigDecimal("price");
        Date requested_at=resultSet.getDate("requested_at");

        return new RoomService(id,reservation_id,service_name,price,requested_at);
    }

    public int getId() { return id; }

    public int getReservation_id() { return reservation_id; }

    public String getService_name() { return service_name; }

    public BigDecimal getPrice() { return price; }

    public Date getRequested_at() { return requested_at; }
}
