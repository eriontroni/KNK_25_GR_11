package Models.DTO;
/*
CREATE TABLE Room (
    id SERIAL PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    type_id INT REFERENCES RoomType(id) ON DELETE SET NULL,
    is_available BOOLEAN DEFAULT TRUE
);
*/
public class CreateRoomDTO {
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    public CreateRoomDTO(String roomNumber, int typeId, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
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
