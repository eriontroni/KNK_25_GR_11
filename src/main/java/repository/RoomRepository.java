package repository;

import Models.Room;
import Models.RoomImage;
import Models.DTO.CreateRoomDTO;
import Models.DTO.UpdateRoomDTO;
import Models.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository extends BaseRepository<Room, CreateRoomDTO, UpdateRoomDTO> {
    public RoomRepository() {
        super("room");
    }

    @Override
    public Room fromResultSet(ResultSet res) {
        try {
            return Room.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomImage> getRoomImages(int roomId) {
        String query = "SELECT * FROM roomimage WHERE room_id = ?";
        List<RoomImage> images = new ArrayList<>();
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, roomId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                RoomImage image = RoomImage.getInstance(rs);
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public RoomType getRoomTypeForRoom(int roomId) {
        String query = "SELECT rt.* FROM room r JOIN roomtype rt ON r.type_id = rt.id WHERE r.id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, roomId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return RoomType.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room getById(int id) {
        String query = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Room room = fromResultSet(rs);
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room create(CreateRoomDTO room) {
        String query = """
                INSERT INTO room (room_number, type_id, is_available)
                VALUES (?, ?, ?)""";
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, room.getRoomNumber());
            pstm.setInt(2, room.getTypeId());
            pstm.setBoolean(3, room.isAvailable());
            pstm.executeUpdate();

            ResultSet result = pstm.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjatë krijimit të dhomës: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Room update(UpdateRoomDTO room) {
        String query = """
                UPDATE room
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
            System.err.println("Gabim gjatë përditësimit të dhomës: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Gabim gjatë fshirjes së dhomës: " + e.getMessage());
        }
        return false;
    }
}
