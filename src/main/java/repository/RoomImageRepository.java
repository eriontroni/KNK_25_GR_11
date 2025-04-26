package repository;

import Models.DTO.CreateRoomImageDTO;
import Models.DTO.UpdateRoomImageDTO;
import Models.RoomImage;

import java.sql.ResultSet;

public class RoomImageRepository extends BaseRepository<RoomImage, CreateRoomImageDTO,UpdateRoomImageDTO> {

    public RoomImageRepository(String tableName) {
        super("RoomImage");
    }

    @Override
    RoomImage fromResutlSet(ResultSet res) {
        return null;
    }

    @Override
    RoomImage create(CreateRoomImageDTO createRoomImageDTO) {
        return null;
    }

    @Override
    RoomImage udpate(UpdateRoomImageDTO updateRoomImageDTO) {
        return null;
    }
}
