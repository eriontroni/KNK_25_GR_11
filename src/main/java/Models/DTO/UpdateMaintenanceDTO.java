package Models.DTO;

public class UpdateMaintenanceDTO {
    private int id;
    private String description;
    private String status;

    public UpdateMaintenanceDTO(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
