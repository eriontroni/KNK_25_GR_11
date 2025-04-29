package Models.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import Models.Reservation;

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
    public static boolean createReservation(Connection connection, Reservation reservation){
        try (PreparedStatement statement = connection.prepareStatement(INSERT_RESERVATION_QUERY)) {
            statement.setInt(1, reservation.getCustomerId()); // vendos ID e klientit
            statement.setInt(2, reservation.getRoomId());     // ID e dhomes
            statement.setDate(3, reservation.getCheckInDate());  //data e hyrjes
            statement.setDate(4,reservation.getCheckOutDate()); //data e daljes
            statement.setString(5, reservation.getStatus());  // statusi
            statement.setDouble(6, reservation.getTotalPrice()); // cmimi

            int rowsAffected = statement.executeUpdate(); // ekzekuton query-n
            return rowsAffected > 0; // kthen true nese rezervimi eshte kriju me sukses
        } catch (SQLException e) {
            e.printStackTrace(); // trajton gabimet ne rast se ndodhin
            return false;
        }
    }
}
