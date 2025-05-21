package repository;

import Models.Notifications;
import Models.DTO.UpdateNotificationDTO;
import Models.DTO.CreateNotificationDTO;

import java.sql.*;
import java.util.ArrayList;

public class NotificationsRepository extends BaseRepository<Notifications, CreateNotificationDTO, UpdateNotificationDTO> {

    public NotificationsRepository() {
        super("notification");
    }

    @Override
    public Notifications fromResultSet(ResultSet res) {
        try {
            return Notifications.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notifications create(CreateNotificationDTO dto) {
        String query = """
            INSERT INTO Notifications (user_id, message, created_at, is_read)
            VALUES (?, ?, ?, ?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getUser_id());
            pstm.setString(2, dto.getMessage());
            pstm.setDate(3, dto.getCreated_at()); // nga Date
            pstm.setBoolean(4, dto.isIs_read());
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
    public Notifications update(UpdateNotificationDTO dto) {
        System.err.println("You must specify the ID to update a specific Notifications. Use custom method instead.");
        return null;
    }

    // Custom update method përmes ID-së
    public Notifications markAsRead(int id, UpdateNotificationDTO dto) {
        String query = """
            UPDATE Notifications
            SET is_read = ?
            WHERE id = ?
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setBoolean(1, dto.isIs_read());
            pstm.setInt(2, id);
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // merr krejt notifications per 1 user
    public ArrayList<Notifications> getAllByUserId(int userId) {
        ArrayList<Notifications> list = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE user_id = ? ORDER BY created_at DESC";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, userId);
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
