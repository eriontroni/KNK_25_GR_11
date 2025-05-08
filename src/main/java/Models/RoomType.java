package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class RoomType {
    private int id;
    private String name;
    private String description;
    private int capacity;
    private float price_per_night;
    private Date created_at;
    private Date updated_at;

    private RoomType(int id, String name, String description, int capacity, float price_per_night, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.price_per_night = price_per_night;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static RoomType getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int capacity = resultSet.getInt("capacity");
        float price_per_night = resultSet.getFloat("price_per_night");
        Date created_at = resultSet.getDate("created_at");
        Date updated_at = resultSet.getDate("updated_at");

        return new RoomType(id, name, description, capacity, price_per_night, created_at, updated_at);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getPrice_per_night() {
        return price_per_night;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}
