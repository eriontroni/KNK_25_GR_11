package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RoomImage {
    private int id;
    private int roomID; // referencon idn e room;
    private String imgURL; //path per deri tek image
    private LocalDateTime  uploadedAt;

    private RoomImage(int id, int roomID, String imgURL, LocalDateTime uploadedAt) {
        this.id = id;
        this.roomID = roomID;
        this.imgURL = imgURL;
        this.uploadedAt = uploadedAt;
    }
    public static RoomImage getInstance(ResultSet resultset) throws SQLException {
        int id = resultset.getInt("id");
        int roomID = resultset.getInt("room_id");
        String imgURL = resultset.getString("img_url");
        String uploadedAtString = resultset.getString("uploaded_at");
        LocalDateTime uploadedAt = LocalDateTime.parse(uploadedAtString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new RoomImage(id, roomID, imgURL, uploadedAt);
    }

    public int getId() {
        return id;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
