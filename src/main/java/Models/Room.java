package Models;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    private final int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;
    private int RoomImage_id;

    private Room(int id, String roomNumber, int typeId, boolean isAvailable, int roomImage_id) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
        this.RoomImage_id = RoomImage_id;
    }

    public static Room getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String roomNumber = resultSet.getString("room_number");
        int typeId = resultSet.getInt("type_id");
        boolean isAvailable = resultSet.getBoolean("is_available");
        int RoomImage_id = resultSet.getInt("RoomImage_id");

        return new Room(id, roomNumber, typeId, isAvailable, RoomImage_id);
    }
    public int getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public int getTypeId() { return typeId; }
    public boolean isAvailable() { return isAvailable; }
    public int getRoomImage_id() { return RoomImage_id; }
}

