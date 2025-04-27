package Models.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CREATE TABLE Reservation (
//        id SERIAL PRIMARY KEY,
//        customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
//room_id INT REFERENCES Room(id) ON DELETE CASCADE,
//check_in_date DATE NOT NULL,
//check_out_date DATE NOT NULL,
//status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
//total_price DECIMAL(10,2) NOT NULL
//);

public class CreateReservationDTO {
    private static final String INSERT_RESERVATION_QUERY = "INSERT INTO Reservation (customer_id, room_id, check_in_date, check_out_date, status, total_price) VALUES (?, ?, ?, ?, ?, ?)";

    // Krijon rezervim dhe e ruan ne databaze
    public static boolean createReservation(Connection connection, int customerId, int roomId, LocalDate checkInDate, LocalDate checkOutDate, String status, double totalPrice) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_RESERVATION_QUERY)) {
            statement.setInt(1, customerId); // vendos ID e klientit
            statement.setInt(2, roomId);     // ID e dhomes
            statement.setString(3, checkInDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // Formatim i datës së hyrjes
            statement.setString(4, checkOutDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // Formatim i datës së daljes
            statement.setString(5, status);  //
            statement.setDouble(6, totalPrice); // cmimi

            int rowsAffected = statement.executeUpdate(); // ekzekuton query-n
            return rowsAffected > 0; // kthen true nese rezervimi eshte kriju me sukses
        } catch (SQLException e) {
            e.printStackTrace(); // trajton gabimet ne rast se ndodhin
            return false;
        }
    }
}
