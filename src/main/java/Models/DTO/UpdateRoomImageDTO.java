package Models.DTO;
//CREATE TABLE RoomImage (
//        id SERIAL PRIMARY KEY,
//        room_id INT REFERENCES Room(id) ON DELETE CASCADE,
//image_url TEXT NOT NULL,
//uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
public class UpdateRoomImageDTO {
    private String image_url;

    UpdateRoomImageDTO(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
