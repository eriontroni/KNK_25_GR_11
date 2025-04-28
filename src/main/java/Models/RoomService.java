package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RoomService {
    private int id;
    private int reservation_id;
    private String service_name;
    private double price;
    private LocalDateTime requested_at;

    public RoomService(int id, int reservation_id, String service_name, double price, LocalDateTime requested_at){
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
        double price=resultSet.getDouble("price");

        String requested_atString= resultSet.getString("requested_at");
        LocalDateTime requested_at = LocalDateTime.parse(requested_atString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return new RoomService(id,reservation_id,service_name,price,requested_at);
    }

    public int getId() { return id; }

    public int getReservation_id() { return reservation_id; }

    public String getService_name() { return service_name; }

    public double getPrice() { return price; }

    public LocalDateTime getRequested_at() { return requested_at; }
}
