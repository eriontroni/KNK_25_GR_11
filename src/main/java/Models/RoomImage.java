package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RoomImage {
    private int id;
    int roomID; // referencon idn e room;
    String imgURL; //path per deri tek image
    String uploadedAt;

    private RoomImage(int id, int roomID, String imgURL, String uploadedAt) {
        this.id = id;
        this.roomID = roomID;
        this.imgURL = imgURL;
        this.uploadedAt = uploadedAt;
    }
    public static RoomImage getInstance(ResultSet resultset) throws SQLException {
        int id = resultset.getInt("id");
        int roomID = resultset.getInt("room_id");
        String imgURL = resultset.getString("img_url");
        String uploadedAt = resultset.getString("uploaded_at");
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

    public String getUploadedAt() {
        return uploadedAt;
    }
}
