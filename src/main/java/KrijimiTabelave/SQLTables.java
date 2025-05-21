package KrijimiTabelave;
import DataBase.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Qet klas e boni run 1 her per me i shti tabelat n databaz;
public class SQLTables {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        /*
         * Erioni - RoomImage, ReservationHistory, Notifications
         * Leoni  - Customer, ReservationDiscount, Offer
         * Natyra - Room, Reservation, Maintenance
         * Vesa   - Users, Payment, Discount
         * Elona  - RoomType, Employee, Event
         * Era    - RoomService, CleaningSchedule, Feedback
         * */
        String query = """

-- 1. RoomType -Elona
CREATE TABLE RoomType (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    capacity INT CHECK (capacity > 0),
    price_per_night DECIMAL(10,2) CHECK (price_per_night >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. RoomImage -Erioni
CREATE TABLE RoomImage (
    id INT PRIMARY KEY,
    image_url TEXT NOT NULL
    room_id INT REFERENCES Room(id) ON DELETE CASCADE
);


-- 5. Users -Vesa
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    salted_hash TEXT NOT NULL
);

-- 6. Employee -Elona
CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    position VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(20),
    password_hash TEXT NOT NULL,
    salted_hash TEXT NOT NULL,
    hire_date DATE
);

-- 15. Offer -Leoni
CREATE TABLE Offer (
    id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    code VARCHAR(50) UNIQUE NOT NULL,
    discount_percentage DECIMAL(5,2) CHECK (discount_percentage BETWEEN 0 AND 100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- 3. Room -Natyra
CREATE TABLE Room (
    id INT PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    type_id INT REFERENCES RoomType(id) ON DELETE SET NULL,
    is_available BOOLEAN DEFAULT TRUE,
);

-- 7. Reservation -Natyra
CREATE TABLE Reservation (
    id INT PRIMARY KEY,
    customer_id INT REFERENCES Users(id) ON DELETE CASCADE,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    offer_id INT REFERENCES Offer(id) ON DELETE SET NULL
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
    total_price DECIMAL(10,2) NOT NULL
);

-- 16. Event -Elona
CREATE TABLE Event (
    id INT PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    organizer_name VARCHAR(255) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    room_id INT REFERENCES Room(id) ON DELETE SET NULL,
    description TEXT
);

-- 14. Maintenance -Natyra
CREATE TABLE Maintenance (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    reported_by INT REFERENCES Employee(id) ON DELETE SET NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'Pending', -- Pending, In Progress, Completed
    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 8. Payment -Vesa
CREATE TABLE Payment (
    id INT PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'Debit Card', 'Cash', 'PayPal')) NOT NULL,
    payment_status VARCHAR(50) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending'
);

-- 9. RoomService -Era
CREATE TABLE RoomService (
    id INT PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    service_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CleaningSchedule (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    employee_id INT REFERENCES Employee(id),
    scheduled_date DATE NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Scheduled', 'Completed', 'Missed')) DEFAULT 'Scheduled'
);

-- 11. Feedback -Era
CREATE TABLE Feedback (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Users(id) ON DELETE CASCADE,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 13. ReservationDiscount -Leoni
CREATE TABLE ReservationDiscount (
    id INT PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    offer_id INT REFERENCES Offer(id) ON DELETE CASCADE
);

-- 17. ReservationHistory -Erioni
CREATE TABLE ReservationHistory (
    id INT PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    customer_id INT REFERENCES Users(id) ON DELETE CASCADE,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    old_status VARCHAR(50),
    new_status VARCHAR(50)
);

-- 18. Notification -Erioni
CREATE TABLE Notification (
    id INT PRIMARY KEY,
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
