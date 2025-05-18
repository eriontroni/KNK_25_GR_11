package Models;



public class Room {
    private final int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;

    public Room(int id,String roomNumber, int typeId, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
    }
    public int getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public int getTypeId() { return typeId; }
    public boolean isAvailable() { return isAvailable; }
}

