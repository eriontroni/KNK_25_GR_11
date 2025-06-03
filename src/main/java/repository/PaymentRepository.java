package repository;

import Models.Payment;
import Models.DTO.CreatePaymentDTO;
import Models.DTO.UpdatePaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepository extends BaseRepository<Payment, CreatePaymentDTO, UpdatePaymentDTO> {

    public PaymentRepository() {
        super("payment");
    }

    @Override
    Payment fromResultSet(ResultSet res) {
        try {
            return Payment.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Payment create(CreatePaymentDTO dto) {
        String query = "INSERT INTO payment (reservation_id, amount, payment_method, payment_status) " +
                "VALUES (?, ?, ?, ?) RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, dto.getReservation_id());
            stmt.setBigDecimal(2, dto.getAmount());
            stmt.setString(3, dto.getPayment_method());
            stmt.setString(4, dto.getPayment_status());

            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return fromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Payment update(UpdatePaymentDTO dto) {
        // Pa ID nuk mund të bëhet update — kjo metodë e BaseRepository duhet të shfrytëzohet vetëm kur ID dihet paraprakisht
        return null;
    }

    // Metodë shtesë për përditësim me ID të dhënë
    public Payment updateById(int id, UpdatePaymentDTO dto) {
        String query = "UPDATE payment SET amount = ?, payment_method = ?, payment_status = ? " +
                "WHERE id = ? RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setBigDecimal(1, dto.getAmount());
            stmt.setString(2, dto.getPayment_method());
            stmt.setString(3, dto.getPayment_status());
            stmt.setInt(4, id);

            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return fromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
