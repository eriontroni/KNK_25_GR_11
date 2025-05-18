package repository;

import Models.Users;
import Models.DTO.CreateUsersDTO;
import Models.DTO.UpdateUsersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository extends BaseRepository<Users, CreateUsersDTO, UpdateUsersDTO> {

    public UsersRepository() {
        super("Users");
    }

    @Override
    Users fromResultSet(ResultSet rs) {
        try {
            return Users.getInstance(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users create(CreateUsersDTO dto) {
        String query = "INSERT INTO Users (username, email, password_hash, salted_hash) " +
                "VALUES (?, ?, ?, ?) RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getEmail());
            stmt.setString(3, dto.getPassword_hash());
            stmt.setString(4, dto.getSalted_hash());

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
    public Users update(UpdateUsersDTO dto) {
        // S'ka kuptim pa ID – përdor metodën më poshtë që pranon edhe ID
        return null;
    }

    public Users updateById(int id, UpdateUsersDTO dto) {
        String query = "UPDATE Users SET username = ?, email = ? WHERE id = ? RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getEmail());
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
