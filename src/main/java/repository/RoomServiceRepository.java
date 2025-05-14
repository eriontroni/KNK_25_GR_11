package repository;

import Models.RoomService;
import Models.DTO.CreateRoomServiceDTO;
import Models.DTO.UpdateRoomServiceDTO;

import java.sql.*;

public class RoomServiceRepository extends BaseRepository<RoomService, CreateRoomServiceDTO, UpdateRoomServiceDTO> {

    public RoomServiceRepository() {
        super("RoomService");
    }

    @Override
    public RoomService fromResultSet(ResultSet res) {
        try {
            return RoomService.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoomService create(CreateRoomServiceDTO roomservice) {
        String query = """
                INSERT INTO RoomService (reservation_id, service_name, price, requested_at)
                VALUES (?, ?, ?, ?)
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, roomservice.getReservationId());
            pstm.setString(2, roomservice.getServiceName());
            pstm.setBigDecimal(3, roomservice.getPrice());
            pstm.setDate(4, new java.sql.Date(roomservice.getRequested_at().getTime()));

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
    public RoomService update(UpdateRoomServiceDTO roomservice) {
        String query = """
                UPDATE RoomService
                SET service_name = ?, price = ?
                WHERE id = ?
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, roomservice.getServiceName());
            pstm.setBigDecimal(2, roomservice.getPrice());
            pstm.setInt(3, roomservice.getId());

            int updatedRecord = pstm.executeUpdate();
            if (updatedRecord == 1) {
                return this.getById(roomservice.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }
}
