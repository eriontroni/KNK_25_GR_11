package repository;

import Models.DTO.CreateRoomImageDTO;
import Models.DTO.UpdateRoomImageDTO;
import Models.RoomImage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomImageRepository extends BaseRepository<RoomImage, CreateRoomImageDTO,UpdateRoomImageDTO> {

    public RoomImageRepository(String tableName) {
        super("RoomImage");
    }

    @Override
    RoomImage fromResutlSet(ResultSet res) {


        return null;
    }

    @Override
    RoomImage create(CreateRoomImageDTO RoomImage) {
        String query = """
                INSERT INTO RoomImage (room_id, image_url, uploaded_at)
                VALUES (?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, RoomImage.getRoom_id());
            pstm.setString(2, RoomImage.getImage_url());
            pstm.setDate(3,RoomImage.getUploaded_at() );
            pstm.execute();
            ResultSet result = pstm.getGeneratedKeys();
            if(result.next()){
                int id = result.getInt(1);
                return this.getById(id);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    RoomImage udpate(UpdateRoomImageDTO RoomImage) {
        String query = """
                UPDATE RoomImage
                SET image_url = ?
                WHERE ID = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, RoomImage.getImage_url());
            pstm.setInt(2, RoomImage.getId());
            int updatedRecord = pstm.executeUpdate();
            if (updatedRecord == 1){
                return this.getById(RoomImage.getId());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
