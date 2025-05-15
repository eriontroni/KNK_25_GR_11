package repository;

import Models.Room;
import Models.DTO.CreateRoomDTO;
import Models.DTO.UpdateRoomDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomRepository extends BaseRepository<Room, CreateRoomDTO, UpdateRoomDTO>{
    public RoomRepository() {
        super("Room");
    }

    @Override
    public Room fromResultSet(ResultSet res) {
        try {
            String roomNumber = res.getString("room_number");
            int typeId = res.getInt("type_id");
            boolean isAvailable = res.getBoolean("is_available");
            return new Room(roomNumber, typeId, isAvailable);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Room create(CreateRoomDTO room) {
        String query = """
                INSERT INTO Room (room_number, type_id, is_available)
                VALUES (?, ?, ?)""";
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, room.getRoomNumber());
            pstm.setInt(2, room.getTypeId());
            pstm.setBoolean(3, room.isAvailable());
            pstm.execute();

            ResultSet result = pstm.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjate krijimit te dhomes: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Room update(UpdateRoomDTO room) {
        String query = """
                UPDATE Room
                SET room_number = ?, type_id = ?, is_available = ?
                WHERE id = ?""";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, room.getRoomNumber());
            pstm.setInt(2, room.getTypeId());
            pstm.setBoolean(3, room.isAvailable());
            pstm.setInt(4, room.getId());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(room.getId());
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjate perditesimit te dhomes: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM Room WHERE id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Gabim gjate fshirjes se dhomes: " + e.getMessage());
        }
        return false;
    }
}
