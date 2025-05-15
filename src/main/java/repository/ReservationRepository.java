package repository;

import Models.Reservation;
import Models.DTO.CreateReservationDTO;
import Models.DTO.UpdateReservationDTO;

import java.sql.*;
import java.util.ArrayList;


public class ReservationRepository extends BaseRepository<Reservation, CreateReservationDTO, UpdateReservationDTO>   {
    public ReservationRepository() {
        super("Reservation");  // emri i tabelës
    }

    @Override
    public Reservation fromResultSet(ResultSet res) {
        try {
            return Reservation.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation create(CreateReservationDTO dto) {
        String query = "INSERT INTO Reservation (customer_id, room_id, check_in_date, check_out_date, status, total_price) VALUES (?, ?, ?, ?, ?, ?) RETURNING *";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setInt(1, dto.getCustomerId());
            pstm.setInt(2, dto.getRoomId());
            pstm.setDate(3, dto.getCheckInDate());
            pstm.setDate(4, dto.getCheckOutDate());
            pstm.setString(5, dto.getStatus());
            pstm.setDouble(6, dto.getTotalPrice());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return Reservation.getInstance(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjatë krijimit të rezervimit: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Reservation update(UpdateReservationDTO dto) {
        String query = "UPDATE Reservation SET check_in_date = ?, check_out_date = ?, status = ?, total_price = ? WHERE id = ? RETURNING *";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setDate(1, dto.getCheckInDate());
            pstm.setDate(2, dto.getCheckOutDate());
            pstm.setString(3, dto.getStatus());
            pstm.setDouble(4, dto.getTotalPrice());
            pstm.setInt(5, dto.getId());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return Reservation.getInstance(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gabim gjatë përditësimit të rezervimit: " + e.getMessage());
        }
        return null;
    }
}
