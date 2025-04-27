package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    private int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    private Room(int id, String roomNumber, int typeId, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
    }

    public static Room getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String roomNumber = resultSet.getString("room_number");
        int typeId = resultSet.getInt("type_id");
        boolean isAvailable = resultSet.getBoolean("is_available");

        return new Room(id, roomNumber, typeId, isAvailable);
    }

    public int getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getTypeId() {
        return typeId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
