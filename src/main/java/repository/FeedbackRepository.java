package repository;

import Models.Feedback;
import Models.DTO.CreateFeedbackDTO;
import Models.DTO.UpdateFeedbackDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeedbackRepository extends BaseRepository<Feedback, CreateFeedbackDTO, UpdateFeedbackDTO> {
    public FeedbackRepository(){
        super("Feedback");
    }

    @Override
    public Feedback fromResultSet(ResultSet res) {
        try {
            return Feedback.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Feedback create(CreateFeedbackDTO feedback) {
        String query = """
                INSERT INTO Feedback (customer_id, reservation_id, rating, comment, created_at)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, feedback.getCustomer_id());
            pstm.setInt(2, feedback.getReservation_id());
            pstm.setInt(3, feedback.getRating());
            pstm.setString(4, feedback.getComment());
            pstm.setDate(5, new java.sql.Date(feedback.getCreated_at().getTime()));

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
    public Feedback update(UpdateFeedbackDTO feedback) {
        String query = """
                UPDATE Feedback
                SET rating = ?, comment = ?
                WHERE id = ?
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, feedback.getRating());
            pstm.setString(2, feedback.getComment());
            pstm.setInt(3, feedback.getId());

            int updatedRecord = pstm.executeUpdate();
            if (updatedRecord == 1) {
                return this.getById(feedback.getId());
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
