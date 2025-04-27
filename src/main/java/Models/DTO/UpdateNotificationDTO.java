package Models.DTO;

import java.time.LocalDateTime;

//-- 18. Notification -Erioni
//CREATE TABLE Notification (
//id SERIAL PRIMARY KEY,
//user_id INT REFERENCES Users(id) ON DELETE CASCADE,
//message TEXT NOT NULL,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//is_read BOOLEAN DEFAULT FALSE

public class UpdateNotificationDTO {
    private boolean is_read;
    UpdateNotificationDTO(boolean is_read) {
        this.is_read = is_read;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
