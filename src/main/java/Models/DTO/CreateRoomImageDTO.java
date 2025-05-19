package Models.DTO;

import java.sql.Date;
import java.time.LocalDateTime;
//
//CREATE TABLE RoomImage (
//        id SERIAL PRIMARY KEY,
//        room_id INT REFERENCES Room(id) ON DELETE CASCADE,
//image_url TEXT NOT NULL,
//uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
public class CreateRoomImageDTO {
    private int room_id;
    private String image_url;


    public CreateRoomImageDTO(int room_id, String image_url) {
        this.room_id = room_id;
        this.image_url = image_url;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


}
