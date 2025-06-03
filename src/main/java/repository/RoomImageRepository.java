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
        super("roomimage");
    }

    @Override
    public RoomImage fromResultSet(ResultSet res) {
        try {
            return RoomImage.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    RoomImage create(CreateRoomImageDTO RoomImage) {
        String query = """
                INSERT INTO roomimage (room_id, image_url, uploaded_at)
                VALUES (?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, RoomImage.getRoom_id());
            pstm.setString(2, RoomImage.getImage_url());
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
    RoomImage update(UpdateRoomImageDTO RoomImage) {
        String query = """
                UPDATE roomimage
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
//        public boolean delete(int id){
//        String query = "DELETE FROM RoomImage WHERE ID = ?";
//        try{
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setInt(1, id);
//            return pstm.executeUpdate() == 1;
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

}
