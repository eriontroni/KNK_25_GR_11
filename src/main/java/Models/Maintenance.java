package Models;

import java.time.LocalDateTime;

public class Maintenance {
    private int id;
    private int roomId;
    private int reportedBy;
    private String description;
    private String status;
    private LocalDateTime reportedAt;

    // konstruktori
    public Maintenance(int id, int roomId, int reportedBy, String description, String status, LocalDateTime reportedAt) {
        this.id = id;
        this.roomId = roomId;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = status;
        this.reportedAt = reportedAt;
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

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }
}
