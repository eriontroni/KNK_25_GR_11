package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
public class Notifications {
    private int id;
    private int userId;
    private String message;
    private Date createdAt;
    private boolean isRead;

    private Notifications(int id, int userId, String message, Date createdAt, boolean isRead) {
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

        Date createdAt = resultSet.getDate("created_at");
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }
}
