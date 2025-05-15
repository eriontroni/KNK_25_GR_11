package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class Maintenance {
    private int id;
    private int roomId;
    private int reportedBy;
    private String description;
    private String status;
    private Date reportedAt;

    public Maintenance(int id, int roomId, int reportedBy, String description, String status, Date reportedAt) {
        this.id = id;
        this.roomId = roomId;
        this.reportedBy = reportedBy;
        this.description = description;
        this.status = status;
        this.reportedAt = reportedAt;
    }
    // Getters
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

    private static final String INSERT_MAINTENANCE_QUERY = "INSERT INTO Maintenance (room_id, reported_by, description, status, reported_at) VALUES (?, ?, ?, ?, ?)";

    //raportim per mirembajtje
    public static boolean createMaintenance(Connection connection, int roomId, int reportedBy, String description, String status, Date reportedAt) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MAINTENANCE_QUERY)) {
            statement.setInt(1, roomId);
            statement.setInt(2, reportedBy);
            statement.setString(3, description);
            statement.setString(4, status);
            statement.setDate(5, reportedAt);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
