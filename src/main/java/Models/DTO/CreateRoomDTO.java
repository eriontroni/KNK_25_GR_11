package Models.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE Room (
    id SERIAL PRIMARY KEY,

@@ -7,17 +11,33 @@ CREATE TABLE Room (
    is_available BOOLEAN DEFAULT TRUE
);
*/

public class CreateRoomDTO {
    private int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    public CreateRoomDTO(int id, String roomNumber, int typeId, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
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