package Models.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE Room (
    id SERIAL PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    type_id INT REFERENCES RoomType(id) ON DELETE SET NULL,
    is_available BOOLEAN DEFAULT TRUE
);
*/

public class CreateRoomDTO {
    private int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    private CreateRoomDTO(int id, String roomNumber, int typeId, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
    }

    public static CreateRoomDTO getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String roomNumber = resultSet.getString("room_number");
        int typeId = resultSet.getInt("type_id");
        boolean isAvailable = resultSet.getBoolean("is_available");

        return new CreateRoomDTO(id, roomNumber, typeId, isAvailable);
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
