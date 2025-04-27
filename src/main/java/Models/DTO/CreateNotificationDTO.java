package Models.DTO;

import java.time.LocalDateTime;
//
//-- 18. Notification -Erioni
//CREATE TABLE Notification (
//        id SERIAL PRIMARY KEY,
//        user_id INT REFERENCES Users(id) ON DELETE CASCADE,
//message TEXT NOT NULL,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//is_read BOOLEAN DEFAULT FALSE
//);
public class CreateNotificationDTO {

    private int user_id;
    private String message;
    private LocalDateTime created_at;
    private boolean is_read;

    public CreateNotificationDTO(int user_id, String message, LocalDateTime created_at, boolean is_read) {
        this.user_id = user_id;
        this.message = message;
        this.created_at = created_at;
        this.is_read = is_read;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
