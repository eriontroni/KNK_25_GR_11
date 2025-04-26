package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notifications {
    private int id;
    private int userId;
    private String message;
    private LocalDateTime createdAt;
    private boolean isRead;

    private Notifications(int id, int userId, String message, LocalDateTime createdAt, boolean isRead) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public static Notifications getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String message = resultSet.getString("message");

        String createdAtString = resultSet.getString("created_at");
        LocalDateTime createdAt = LocalDateTime.parse(createdAtString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        boolean isRead = resultSet.getBoolean("is_read");

        return new Notifications(id, userId, message, createdAt, isRead);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }
}
