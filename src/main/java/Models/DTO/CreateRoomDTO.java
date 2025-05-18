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
    private final String roomNumber;
    private final int typeId;
    private final boolean isAvailable;

    private CreateRoomDTO(String roomNumber, int typeId, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
    }

    public static CreateRoomDTO fromResultSet(ResultSet rs) throws SQLException {
        return new CreateRoomDTO(
                rs.getString("room_number"),
                rs.getInt("type_id"),
                rs.getBoolean("is_available")
        );
    }

    public String getRoomNumber() { return roomNumber; }
    public int getTypeId() { return typeId; }
    public boolean isAvailable() { return isAvailable; }
}
