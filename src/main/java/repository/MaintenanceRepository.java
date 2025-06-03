package repository;

import Models.Maintenance;
import Models.DTO.CreateMaintenanceDTO;
import Models.DTO.UpdateMaintenanceDTO;

import java.sql.*;
import java.util.ArrayList;


public class MaintenanceRepository extends BaseRepository<Maintenance, CreateMaintenanceDTO, UpdateMaintenanceDTO> {
    public MaintenanceRepository() {
        super("maintenance");
    }

    @Override
    public Maintenance fromResultSet(ResultSet res) {
        try {
            // Krijojmë Maintenance nga ResultSet
            int id = res.getInt("id");
            int roomId = res.getInt("room_id");
            int reportedBy = res.getInt("reported_by");
            String description = res.getString("description");
            String status = res.getString("status");
            Date reportedAt = res.getDate("reported_at");

            // Krijojmë objektin Maintenance - duhet të bësh konstruktor ose metodë statike në Maintenance për këtë,
            // por nëse s’ke, e krijojmë direkt këtu:
            return new Maintenance(id, roomId, reportedBy, description, status, reportedAt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Maintenance create(CreateMaintenanceDTO dto) {
        String query = "INSERT INTO Maintenance (room_id, reported_by, description, status, reported_at) VALUES (?, ?, ?, ?, ?) RETURNING *";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setInt(1, dto.getRoomId());
            pstm.setInt(2, dto.getReportedBy());
            pstm.setString(3, dto.getDescription());
            pstm.setString(4, dto.getStatus());
            pstm.setDate(5, dto.getReportedAt());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjatë krijimit të Maintenance: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Maintenance update(UpdateMaintenanceDTO dto) {
        String query = "UPDATE Maintenance SET description = ?, status = ? WHERE id = ? RETURNING *";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, dto.getDescription());
            pstm.setString(2, dto.getStatus());
            pstm.setInt(3, dto.getId());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjatë përditësimit të Maintenance: " + e.getMessage());
        }
        return null;
    }
}
