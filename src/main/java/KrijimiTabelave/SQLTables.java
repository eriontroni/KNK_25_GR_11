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
                CREATE TABLE Room (
                    id SERIAL PRIMARY KEY,
                    room_number VARCHAR(10) UNIQUE NOT NULL,
                    type VARCHAR(50) NOT NULL,
                    capacity INT NOT NULL,
                    price_per_night DECIMAL(10,2) NOT NULL,
                    is_available BOOLEAN DEFAULT TRUE
                );
                
                CREATE TABLE Customer (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL,
                    phone VARCHAR(20) UNIQUE NOT NULL
                );
                
                CREATE TABLE Reservation (
                    id SERIAL PRIMARY KEY,
                    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
                    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                    check_in_date DATE NOT NULL,
                    check_out_date DATE NOT NULL,
                    status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
                    total_price DECIMAL(10,2) NOT NULL
                );
                
                CREATE TABLE Payment (
                    id SERIAL PRIMARY KEY,
                    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                    amount DECIMAL(10,2) NOT NULL,
                    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'Debit Card', 'Cash', 'PayPal')) NOT NULL,
                    payment_status VARCHAR(50) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending'
                );
                
                CREATE TABLE Users (
                    id SERIAL PRIMARY KEY,
                    username VARCHAR(100) UNIQUE NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL,
                    password_hash TEXT NOT NULL,
                    role VARCHAR(50) CHECK (role IN ('Admin', 'Receptionist', 'Customer')) NOT NULL
                );
                
                CREATE TABLE Employee (
    
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL,
                    position VARCHAR(100),
                    email VARCHAR(255) UNIQUE,
                    phone VARCHAR(20),
                    hire_date DATE                
);
            CREATE TABLE Room_Service (
                id SERIAL PRIMARY KEY,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                service_name VARCHAR(100) NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            CREATE TABLE Cleaning_Schedule (
                id SERIAL PRIMARY KEY,
                room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                employee_id INT REFERENCES Employee(id),
                scheduled_date DATE NOT NULL,
                status VARCHAR(50) CHECK (status IN ('Scheduled', 'Completed', 'Missed')) DEFAULT 'Scheduled'
            );
            CREATE TABLE Feedback (
                id SERIAL PRIMARY KEY,
                customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                rating INT CHECK (rating BETWEEN 1 AND 5),
                comment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            CREATE TABLE Room_Image (
                id SERIAL PRIMARY KEY,
                room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                image_url TEXT NOT NULL,
                uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            CREATE TABLE Discount (
                id SERIAL PRIMARY KEY,
                code VARCHAR(50) UNIQUE NOT NULL,
                description TEXT,
                percentage DECIMAL(5,2) CHECK (percentage > 0 AND percentage <= 100),
                valid_from DATE NOT NULL,
                valid_to DATE NOT NULL
            );
            
            CREATE TABLE Reservation_Discount (
                id SERIAL PRIMARY KEY,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                discount_id INT REFERENCES Discount(id) ON DELETE CASCADE
            );



                CREATE TABLE maintenance_requests (
                    id SERIAL PRIMARY KEY,
                    room_id INT REFERENCES rooms(id),
                    reported_by INT REFERENCES employees(id),
                    description TEXT,
                    status VARCHAR(20) DEFAULT 'pending', -- pending, in_progress, completed
                    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
