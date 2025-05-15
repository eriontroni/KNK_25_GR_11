package repository;

import DataBase.DBConnector;
import Models.Discount;
import Models.DTO.CreateDiscountDTO;
import Models.DTO.UpdateDiscountDTO;

import java.sql.*;
import java.util.ArrayList;

public class DiscountRepository extends BaseRepository<Discount, CreateDiscountDTO, UpdateDiscountDTO> {

    public DiscountRepository() {
        super("Discount");
    }

    @Override
    Discount fromResultSet(ResultSet res) {
        try {
            return Discount.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Discount create(CreateDiscountDTO dto) {
        String query = "INSERT INTO Discount (code, description, percentage, valid_from, valid_to) VALUES (?, ?, ?, ?, ?) RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dto.getCode());
            stmt.setString(2, dto.getDescription());
            stmt.setBigDecimal(3, dto.getPercentage());
            stmt.setDate(4, new java.sql.Date(dto.getValid_from().getTime()));
            stmt.setDate(5, new java.sql.Date(dto.getValid_to().getTime()));

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
    public Discount update(UpdateDiscountDTO dto) {

        String query = "UPDATE Discount SET description = ?, percentage = ?, valid_from = ?, valid_to = ? WHERE code = ? RETURNING *";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dto.getDescription());
            stmt.setBigDecimal(2, dto.getPercentage());
            stmt.setDate(3, new java.sql.Date(dto.getValid_from().getTime()));
            stmt.setDate(4, new java.sql.Date(dto.getValid_to().getTime()));

            String codeToUpdate = "DISCOUNT_CODE"; // zëvendësoje me vlerën aktuale

            stmt.setString(5, codeToUpdate);

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
