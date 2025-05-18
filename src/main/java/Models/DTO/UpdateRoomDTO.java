package Models.DTO;

public class UpdateRoomDTO {
    private final int id;
    private final String roomNumber;
    private final Integer typeId;
    private final Boolean isAvailable;

    public UpdateRoomDTO(int id, String roomNumber, Integer typeId, Boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;  // Lejojmë null për përditësime të pjesshme
        this.isAvailable = isAvailable;
    }

    public int getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public Integer getTypeId() { return typeId; }
    public Boolean isAvailable() { return isAvailable; }


    public boolean shouldUpdateRoomNumber() {
        return roomNumber != null && !roomNumber.trim().isEmpty();
    }

    public boolean shouldUpdateTypeId() {
        return typeId != null && typeId > 0;
    }

    public boolean shouldUpdateAvailability() {
        return isAvailable != null;
    }

    // Metodë për validim
    public boolean isValid() {
        return id > 0 && (shouldUpdateRoomNumber() || shouldUpdateTypeId() || shouldUpdateAvailability());
    }
}
