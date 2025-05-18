package Models;

public class Room {
    private int id;
    private String roomNumber;
    private int typeId;
    private boolean isAvailable;
    private String roomTypeName;
    private double pricePerNight;
    private String status;

    public Room(int id, String roomNumber, int typeId, boolean isAvailable,
                String roomTypeName, double pricePerNight, String status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.isAvailable = isAvailable;
        this.roomTypeName = roomTypeName;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    //Getters
    public int getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public int getTypeId() { return typeId; }
    public boolean isAvailable() { return isAvailable; }
    public String getRoomTypeName() { return roomTypeName; }
    public double getPricePerNight() { return pricePerNight; }
    public String getStatus() { return status; }


    // Setters for mutable fields
    public void setId(int id) { this.id = id; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setStatus(String status) { this.status = status; }
}
