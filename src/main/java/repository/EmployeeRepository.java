package repository;

import DataBase.DBConnector;
import Models.Employee;
import Models.DTO.createEmployeeDTO;
import Models.DTO.updateEmployeeDTO;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeRepository extends BaseRepository<Employee, createEmployeeDTO, updateEmployeeDTO> {

    public EmployeeRepository() {
        super("Employee");
    }

    @Override
    public Employee fromResultSet(ResultSet res) {
        try {
            return Employee.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getByEmail(String email) {
        String query = "SELECT * FROM employees WHERE email = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Employee.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Employee create(createEmployeeDTO dto) {
        String query = """
                INSERT INTO Employee (first_name, last_name, position, email, password_hash, salted_hash, phone, hire_date)
                VALUES (?, ?, ?,?,?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getFirst_name());
            pstm.setString(2, dto.getLast_name());
            pstm.setString(3, dto.getPosition());
            pstm.setString(4, dto.getEmail());
            pstm.setString(6, dto.getSalted_hash());
            pstm.setString(5, dto.getPassword_hash());
            pstm.setString(7, dto.getPhone());
            pstm.setDate(8, new java.sql.Date(System.currentTimeMillis()));
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
    public Employee update(updateEmployeeDTO dto) {
        String query = """
                UPDATE Employee
                SET position = ?, email = ?, phone = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getPosition());
            pstm.setString(2, dto.getEmail());
            pstm.setString(3, dto.getPhone());
            pstm.setInt(4, dto.getId());

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