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
                --1
                CREATE TABLE Room (
                    id SERIAL PRIMARY KEY,
                    room_number VARCHAR(10) UNIQUE NOT NULL,
                    type VARCHAR(50) NOT NULL,
                    capacity INT NOT NULL,
                    price_per_night DECIMAL(10,2) NOT NULL,
                    is_available BOOLEAN DEFAULT TRUE
                );
                --2
                CREATE TABLE Customer (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL,
                    phone VARCHAR(20) UNIQUE NOT NULL
                );
                --3
                CREATE TABLE Reservation (
                    id SERIAL PRIMARY KEY,
                    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
                    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                    check_in_date DATE NOT NULL,
                    check_out_date DATE NOT NULL,
                    status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
                    total_price DECIMAL(10,2) NOT NULL
                );
                --4
                CREATE TABLE Payment (
                    id SERIAL PRIMARY KEY,
                    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                    amount DECIMAL(10,2) NOT NULL,
                    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'Debit Card', 'Cash', 'PayPal')) NOT NULL,
                    payment_status VARCHAR(50) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending'
                );
                --5
                CREATE TABLE Users (
                    id SERIAL PRIMARY KEY,
                    username VARCHAR(100) UNIQUE NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL,
                    password_hash TEXT NOT NULL,
                    role VARCHAR(50) CHECK (role IN ('Admin', 'Receptionist', 'Customer')) NOT NULL
                );
                --6
                CREATE TABLE Employee (
    
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL,
                    position VARCHAR(100),
                    email VARCHAR(255) UNIQUE,
                    phone VARCHAR(20),
                    hire_date DATE                
);          --7  
            CREATE TABLE Room_Service (
                id SERIAL PRIMARY KEY,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                service_name VARCHAR(100) NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            --8
            CREATE TABLE Cleaning_Schedule (
                id SERIAL PRIMARY KEY,
                room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                employee_id INT REFERENCES Employee(id),
                scheduled_date DATE NOT NULL,
                status VARCHAR(50) CHECK (status IN ('Scheduled', 'Completed', 'Missed')) DEFAULT 'Scheduled'
            );
            --9
            CREATE TABLE Feedback (
                id SERIAL PRIMARY KEY,
                customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                rating INT CHECK (rating BETWEEN 1 AND 5),
                comment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            --10
            CREATE TABLE Room_Image (
                id SERIAL PRIMARY KEY,
                room_id INT REFERENCES Room(id) ON DELETE CASCADE,
                image_url TEXT NOT NULL,
                uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            --11
            CREATE TABLE Discount (
                id SERIAL PRIMARY KEY,
                code VARCHAR(50) UNIQUE NOT NULL,
                description TEXT,
                percentage DECIMAL(5,2) CHECK (percentage > 0 AND percentage <= 100),
                valid_from DATE NOT NULL,
                valid_to DATE NOT NULL
            );
            --12
            CREATE TABLE Reservation_Discount (
                id SERIAL PRIMARY KEY,
                reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
                discount_id INT REFERENCES Discount(id) ON DELETE CASCADE
            );
            --13
            CREATE TABLE Maintenance (
                id SERIAL PRIMARY KEY,
                room_id INT REFERENCES rooms(id),
                reported_by INT REFERENCES employees(id),
                description TEXT,
                status VARCHAR(20) DEFAULT 'pending', -- pending, in_progress, completed
                reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
           --14
           CREATE TABLE room_types (
               id SERIAL PRIMARY KEY,
               name VARCHAR(100) NOT NULL,
               description TEXT,
               capacity INT CHECK (capacity > 0),
               price_per_night DECIMAL(10, 2) CHECK (price_per_night >= 0),
               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
         );
                --15
                CREATE TABLE Feedback (
                    id SERIAL PRIMARY KEY,
                    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
                    comment TEXT NOT NULL,
                    rating INT CHECK (rating BETWEEN 1 AND 5) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                  --16
                  CREATE TABLE DiscountsAndOffers (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      description TEXT,
                      discount_percentage DECIMAL(5,2) CHECK (discount_percentage BETWEEN 0 AND 100) NOT NULL,
                      start_date DATE NOT NULL,
                      end_date DATE NOT NULL
                  );
                  --17
                  CREATE TABLE Events (
                      event_id SERIAL PRIMARY KEY,
                      event_name VARCHAR(255) NOT NULL,
                      organizer_name VARCHAR(255) NOT NULL,
                      event_date DATE NOT NULL,
                      event_time TIME NOT NULL,
                      room_id INT REFERENCES Room(id) ON DELETE SET NULL,
                      description TEXT
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
