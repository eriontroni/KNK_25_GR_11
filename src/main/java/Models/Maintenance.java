package Models;

import java.sql.Date;

public class Maintenance {
    private int id;
    private int roomId;
    private int reportedBy;
    private String description;
    private String status;
    private Date reportedAt;

    // konstruktori
    public Maintenance(int id, int roomId, int reportedBy, String description, String status, Date reportedAt) {
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

    public Date getReportedAt() {
        return reportedAt;
    }
}
