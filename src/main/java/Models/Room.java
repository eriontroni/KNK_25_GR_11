package Models;

public class Room {
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    public Room(String roomNumber, int typeId, boolean isAvailable) {
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
