package repository;

import Models.DTO.CreateReservationHistoryDTO;
import Models.DTO.UpdateReservationHistoryDTO;
import Models.ReservationHistory;

import java.sql.*;
import java.util.ArrayList;

public class ReservationHistoryRepository extends BaseRepository<ReservationHistory, CreateReservationHistoryDTO, UpdateReservationHistoryDTO> {

    public ReservationHistoryRepository() {
        super("reservationhistory");
    }

    @Override
    public ReservationHistory fromResultSet(ResultSet res) {
        try {
            return ReservationHistory.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReservationHistory create(CreateReservationHistoryDTO dto) {
        String query = """
                INSERT INTO reservationhistory (reservation_id, customer_id, change_date, old_status, new_status)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getReservation_id());
            pstm.setInt(2, dto.getCustomer_id());
            pstm.setTimestamp(3, new Timestamp(dto.getChange_date().getTime())); // nga java.util.Date në SQL Timestamp
            pstm.setString(4, dto.getOld_status());
            pstm.setString(5, dto.getNew_status());

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
    public ReservationHistory update(UpdateReservationHistoryDTO dto) {
        String query = """
                UPDATE ReservationHistory
                SET old_status = ?, new_status = ?
                WHERE reservation_id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getOld_status());
            pstm.setString(2, dto.getNew_status());
            pstm.setInt(3, dto.getId());
            int updatedRows = pstm.executeUpdate();
            if (updatedRows == 1) {
                return getLatestByReservationId(dto.getId());
            }
        } catch (SQLException e) {     
            e.printStackTrace();
        }
        return null;
    }


    public ReservationHistory getLatestByReservationId(int reservationId) {
        String query = """
                SELECT * FROM ReservationHistory
                WHERE reservation_id = ?
                ORDER BY change_date DESC
                LIMIT 1
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, reservationId);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return fromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // imerr krejt historite per 1 id rezervimi
    public ArrayList<ReservationHistory> getAllByReservationId(int reservationId) {
        ArrayList<ReservationHistory> list = new ArrayList<>();
        String query = "SELECT * FROM ReservationHistory WHERE reservation_id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, reservationId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
