package KrijimiTabelave;
import DataBase.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// qet klas e boni run 1 her per me i shti tabelat n databaz;
public class SQLTables {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        String query = """
-- 1. RoomType
CREATE TABLE RoomType (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    capacity INT CHECK (capacity > 0),
    price_per_night DECIMAL(10,2) CHECK (price_per_night >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Room
CREATE TABLE Room (
    id SERIAL PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    type_id INT REFERENCES RoomType(id) ON DELETE SET NULL,
    is_available BOOLEAN DEFAULT TRUE
);

-- 3. Customer
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL
);

-- 4. Users
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role VARCHAR(50) CHECK (role IN ('Admin', 'Receptionist', 'Customer')) NOT NULL
);

-- 5. Employee
CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    position VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(20),
    hire_date DATE
);

-- 6. Reservation
CREATE TABLE Reservation (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
    total_price DECIMAL(10,2) NOT NULL
);

-- 7. Payment
CREATE TABLE Payment (
    id SERIAL PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'Debit Card', 'Cash', 'PayPal')) NOT NULL,
    payment_status VARCHAR(50) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending'
);

-- 8. RoomService
CREATE TABLE RoomService (
    id SERIAL PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    service_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. CleaningSchedule
CREATE TABLE CleaningSchedule (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    employee_id INT REFERENCES Employee(id),
    scheduled_date DATE NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Scheduled', 'Completed', 'Missed')) DEFAULT 'Scheduled'
);

-- 10. Feedback
CREATE TABLE Feedback (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 11. RoomImage
CREATE TABLE RoomImage (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    image_url TEXT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 12. Discount
CREATE TABLE Discount (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    percentage DECIMAL(5,2) CHECK (percentage > 0 AND percentage <= 100),
    valid_from DATE NOT NULL,
    valid_to DATE NOT NULL
);

-- 13. ReservationDiscount
CREATE TABLE ReservationDiscount (
    id SERIAL PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    discount_id INT REFERENCES Discount(id) ON DELETE CASCADE
);

-- 14. Maintenance
CREATE TABLE Maintenance (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    reported_by INT REFERENCES Employee(id) ON DELETE SET NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'Pending', -- Pending, In Progress, Completed
    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 15. Offer
CREATE TABLE Offer (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    discount_percentage DECIMAL(5,2) CHECK (discount_percentage BETWEEN 0 AND 100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- 16. Event
CREATE TABLE Event (
    id SERIAL PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    organizer_name VARCHAR(255) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    room_id INT REFERENCES Room(id) ON DELETE SET NULL,
    description TEXT
);

-- 17. ReservationHistory
CREATE TABLE ReservationHistory (
    id SERIAL PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    old_status VARCHAR(50),
    new_status VARCHAR(50)
);

-- 18. Notification
CREATE TABLE Notification (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE
);

                """;

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
}


}
