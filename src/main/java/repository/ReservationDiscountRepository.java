package repository;

import Models.DTO.CreateReservationDiscountDTO;
import Models.DTO.UpdateReservationDiscountDTO;
import Models.ReservationDiscount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationDiscountRepository extends BaseRepository<ReservationDiscount, CreateReservationDiscountDTO, UpdateReservationDiscountDTO> {

    public ReservationDiscountRepository(String tableName) {
        super("reservationdiscount");
    }

    @Override
    public ReservationDiscount fromResultSet(ResultSet res) {
        try {
            return ReservationDiscount.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    ReservationDiscount create(CreateReservationDiscountDTO dto) {
        String query = """
                INSERT INTO ReservationDiscount (reservation_id, discount_id)
                VALUES (?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getReservation_id());
            pstm.setInt(2, dto.getDiscount_id());
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
    ReservationDiscount update(UpdateReservationDiscountDTO dto) {
        String query = """
                UPDATE ReservationDiscount
                SET reservation_id = ?, discount_id = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, dto.getReservation_id());
            pstm.setInt(2, dto.getDiscount_id());
            pstm.setInt(3, dto.getId());

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
