package Models.DTO;

import java.sql.Date;

//CREATE TABLE Maintenance (
//        id SERIAL PRIMARY KEY,
//        room_id INT REFERENCES Room(id) ON DELETE CASCADE,
//reported_by INT REFERENCES Employee(id) ON DELETE SET NULL,
//description TEXT,
//status VARCHAR(20) DEFAULT 'Pending', -- Pending, In Progress, Completed
//reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);

public class CreateMaintenanceDTO {
    private int id;
    private int roomId;
    private int reportedBy;
    private String description;
    private String status;
    private Date reportedAt;

    // konstruktori
    public CreateMaintenanceDTO(int id, int roomId, int reportedBy, String description, String status, Date reportedAt) {
        this.id = id;
        this.roomId = roomId;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = status;
        this.reportedAt = reportedAt;
    }

    public CreateMaintenanceDTO(int roomId, int reportedBy, String description, String status) {
        this.roomId = roomId;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = status;
    }

    // getter per fusha
    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getReportedBy() {
        return reportedBy;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getReportedAt() {
        return reportedAt;
    }
}
