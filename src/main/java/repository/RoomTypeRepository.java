package repository;

import Models.RoomType;
import Models.DTO.createRoomTypeDTO;
import Models.DTO.updateRoomTypeDTO;

import java.sql.*;
import java.util.Date;

public class RoomTypeRepository extends BaseRepository<RoomType, createRoomTypeDTO, updateRoomTypeDTO> {

    public RoomTypeRepository() {
        super("RoomType");
    }

    @Override
    public RoomType fromResultSet(ResultSet res) {
        try {
            return RoomType.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoomType create(createRoomTypeDTO dto) {
        String query = """
                INSERT INTO RoomType (name, description, capacity, price_per_night, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getName());
            pstm.setString(2, dto.getDescription());
            pstm.setInt(3, dto.getCapacity());
            pstm.setFloat(4, dto.getPrice_per_night());
            pstm.setTimestamp(5, new Timestamp(dto.getCreatedAt().getTime()));
            pstm.setTimestamp(6, new Timestamp(dto.getUpdatedAt().getTime()));
            pstm.execute();

            ResultSet result = pstm.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoomType update(updateRoomTypeDTO dto) {
        String query = """
                UPDATE RoomType
                SET description = ?, capacity = ?, price_per_night = ?, updated_at = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getDescription());
            pstm.setInt(2, dto.getCapacity());
            pstm.setFloat(3, dto.getPrice_per_night());
            pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // përditësohet automatikisht
            pstm.setInt(5, dto.getId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

