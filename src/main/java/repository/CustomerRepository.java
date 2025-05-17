package repository;

import DataBase.DBConnector;
import Models.Customer;
import Models.DTO.CreateCustomerDTO;
import Models.DTO.UpdateCustomerDTO;

import java.sql.*;

public class CustomerRepository extends BaseRepository<Customer, CreateCustomerDTO, UpdateCustomerDTO> {

    public CustomerRepository(String tableName) {
        super("Customer");
    }

    @Override
    public Customer fromResultSet(ResultSet res) {
        try {
            return Customer.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Customer getByEmail(String email) {
        String query = "SELECT * FROM customers WHERE email = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Customer.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    Customer create(CreateCustomerDTO dto) {
        String query = """
                INSERT INTO Customer (first_name, last_name, email, phone)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getFirst_name());
            pstm.setString(2, dto.getLast_name());
            pstm.setString(3, dto.getEmail());
            pstm.setString(4, dto.getPhone());
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
    Customer update(UpdateCustomerDTO dto) {
        String query = """
                UPDATE Customer
                SET first_name = ?, last_name = ?, email = ?, phone = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getFirst_name());
            pstm.setString(2, dto.getLast_name());
            pstm.setString(3, dto.getEmail());
            pstm.setString(4, dto.getPhone());
            pstm.setInt(5, dto.getId());

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
