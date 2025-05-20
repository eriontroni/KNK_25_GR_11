package KrijimiTabelave;

import DataBase.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataLog {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        String query = """
-- Insert në tabelën RoomType
INSERT INTO RoomType (name, description, capacity, price_per_night)
VALUES
('Single', 'Dhomë me një krevat single', 1, 30.00),
('Double', 'Dhomë me një krevat dopio', 2, 55.00),
('Suite', 'Dhomë suite luksoze', 4, 120.00),
('Family', 'Dhomë e madhe familjare', 5, 95.00),
('Economy', 'Dhomë ekonomike me facilitete bazë', 1, 25.00);

-- Insert në tabelën Room (sigurohet që type_id është i saktë nga RoomType)
INSERT INTO Room (room_number, type_id, is_available,RoomImage_id)
VALUES
('101', 1, TRUE,5),
('102', 2, TRUE,4),
('201', 3, FALSE,3),
('202', 4, TRUE,1),
('301', 5, TRUE,2);

-- Insert në tabelën Customer (unik në email dhe telefon)
--INSERT INTO Customer (first_name, last_name, email, phone)
--VALUES
--('Albin', 'Krasniqi', 'albinkrasniqi@example.com', '+38344111222'),
--('Elisa', 'Berisha', 'elisaberisha@example.com', '+38344111333'),
--('Arber', 'Hoti', 'arberhoti@example.com', '+38344111444'),
--('Sara', 'Gashi', 'saragashi@example.com', '+38344111555'),
--('Dion', 'Bytyqi', 'dionbytyqi@example.com', '+38344111666');


-- Insert në tabelën Users (klientë)
--INSERT INTO Users (username, email, password_hash, salted_hash)
--VALUES
--('klient_aferdita', 'aferdita.klient@example.com', 'hashed_pass7', 'salted_hash7'),
--('klient_dren', 'dren.klient@example.com', 'hashed_pass8', 'salted_hash8'),
--('klient_alba', 'alba.klient@example.com', 'hashed_pass9', 'salted_hash9'),
--('klient_erion', 'erion.klient@example.com', 'hashed_pass10', 'salted_hash10'),
--('klient_fiona', 'fiona.klient@example.com', 'hashed_pass11', 'salted_hash11');

-- Insert në tabelën Employee
--INSERT INTO Employee (first_name, last_name, position, email, phone, password_hash, salted_hash, hire_date)
--VALUES
-- Recepsionistë
--('Erza', 'Krasniqi', 'Receptionist', 'erza.krasniqi@example.com', '+38344111001', 'hashed_pass1', 'salted_hash1', '2022-01-10'),
--('Luan', 'Gashi', 'Receptionist', 'luan.gashi@example.com', '+38344111002', 'hashed_pass2', 'salted_hash2', '2021-11-15'),

-- Mirëmbajtës
--('Arta', 'Hoxha', 'Mirëmbajtëse', 'arta.hoxha@example.com', '+38344111003', 'hashed_pass3', 'salted_hash3', '2023-02-20'),
--('Dion', 'Beka', 'Mirëmbajtës', 'dion.beka@example.com', '+38344111004', 'hashed_pass4', 'salted_hash4', '2023-05-18'),
--('Vjollca', 'Rama', 'Mirëmbajtëse', 'vjollca.rama@example.com', '+38344111005', 'hashed_pass5', 'salted_hash5', '2022-09-07'),
--('Florian', 'Tahiri', 'Mirëmbajtës', 'florian.tahiri@example.com', '+38344111006', 'hashed_pass6', 'salted_hash6', '2023-01-12');


-- Insert në tabelën Reservation (customer_id dhe room_id duhet të jenë ekzistuese nga tabela Customer dhe Room)
INSERT INTO Reservation (customer_id, room_id, check_in_date, check_out_date, status, total_price)
VALUES
(1, 2, '2024-06-10', '2024-06-12', 'Confirmed', 120.00),
(2, 3, '2024-07-15', '2024-07-20', 'Pending', 300.00),
(3, 1, '2024-06-05', '2024-06-07', 'Cancelled', 70.00),
(4, 4, '2024-08-01', '2024-08-05', 'Confirmed', 400.00),
(5, 5, '2024-09-10', '2024-09-15', 'Pending', 125.00);

-- Insert në tabelën Payment
INSERT INTO Payment (reservation_id, amount, payment_method, payment_status)
VALUES
(1, 120.00, 'Credit Card', 'Completed'),
(2, 300.00, 'PayPal', 'Pending'),
(3, 70.00, 'Cash', 'Failed'),
(4, 400.00, 'Debit Card', 'Completed'),
(5, 125.00, 'Credit Card', 'Completed');

-- Insert në tabelën RoomService
INSERT INTO RoomService (reservation_id, service_name, price, requested_at)
VALUES
(1, 'Mëngjes në dhomë', 15.00, CURRENT_TIMESTAMP),
(2, 'Larje Rrobash', 10.00, CURRENT_TIMESTAMP),
(3, 'Masazh SPA', 50.00, CURRENT_TIMESTAMP),
(4, 'Darka Speciale', 30.00, CURRENT_TIMESTAMP),
(5, 'Pastrim Ekstra', 20.00, CURRENT_TIMESTAMP);

-- Insert në tabelën CleaningSchedule
INSERT INTO CleaningSchedule (room_id, employee_id, scheduled_date, status)
VALUES
(1, 4, '2024-06-11', 'Scheduled'),
(2, 5, '2024-07-16', 'Completed'),
(3, 4, '2024-06-06', 'Missed'),
(4, 5, '2024-08-03', 'Scheduled'),
(5, 4, '2024-09-12', 'Scheduled');

-- Insert në tabelën Feedback
INSERT INTO Feedback (customer_id, reservation_id, rating, comment, created_at)
VALUES
(1, 1, 5, 'Shërbim shumë i mirë, ambient i këndshëm.', CURRENT_TIMESTAMP),
(2, 2, 4, 'Dhoma ishte e pastër dhe e rregullt.', CURRENT_TIMESTAMP),
(3, 3, 2, 'Pata probleme me rezervimin tim.', CURRENT_TIMESTAMP),
(4, 4, 3, 'Mirë, por mund të përmirësohet.', CURRENT_TIMESTAMP),
(5, 5, 5, 'Fantastike! Do ta rekomandoja!', CURRENT_TIMESTAMP);

-- Insert në tabelën RoomImage
INSERT INTO RoomImage (image_url)
VALUES
('/images/img.png'),
('/images/img_1.png'),
('/images/img_2.png'),
('/images/img_3.png'),
('/images/img_4.png');

-- Insert në tabelën Discount
INSERT INTO Discount (code, description, percentage, valid_from, valid_to)
VALUES
('SUMMER20', 'Ulje vere 20%', 20.00, '2024-06-01', '2024-08-31'),
('WELCOME10', 'Ulje për klientët e rinj 10%', 10.00, '2024-05-01', '2024-12-31'),
('FESTIVE15', 'Ulje gjatë festave 15%', 15.00, '2024-12-01', '2024-12-31'),
('WEEKEND5', 'Ulje për fundjavë 5%', 5.00, '2024-05-01', '2024-09-30'),
('VIP25', 'Ulje speciale për klientët VIP 25%', 25.00, '2024-01-01', '2024-12-31');

-- Insert në tabelën ReservationDiscount
INSERT INTO ReservationDiscount (reservation_id, discount_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insert në tabelën Maintenance
INSERT INTO Maintenance (room_id, reported_by, description, status, reported_at)
VALUES
(1, 4, 'Problem me kondicionerin, nuk ftoh mirë.', 'Pending', CURRENT_TIMESTAMP),
(2, 5, 'Lavaman i bllokuar.', 'In Progress', CURRENT_TIMESTAMP),
(3, 4, 'Ndriçimi në banjë nuk funksionon.', 'Completed', CURRENT_TIMESTAMP),
(4, 5, 'Dera e ballkonit nuk mbyllet mirë.', 'Pending', CURRENT_TIMESTAMP),
(5, 4, 'Probleme me televizorin.', 'In Progress', CURRENT_TIMESTAMP);

-- Insert në tabelën Offer
INSERT INTO Offer (title, description, discount_percentage, start_date, end_date)
VALUES
('Oferta Pranverore', 'Ulje speciale për muajin Prill dhe Maj', 15.00, '2024-04-01', '2024-05-31'),
('Fundjava Relax', 'Ulje 10% për fundjava', 10.00, '2024-06-01', '2024-08-31'),
('Oferta për Çiftet', 'Ulje speciale 20% për çiftet', 20.00, '2024-02-01', '2024-12-31'),
('Oferta Ditore', 'Ulje ditore për rezervime të minutës së fundit', 5.00, '2024-05-01', '2024-12-31'),
('Oferta VIP', 'Ulje 25% për klientët VIP', 25.00, '2024-01-01', '2024-12-31');

-- Insert në tabelën Event
INSERT INTO Event (event_name, organizer_name, event_date, event_time, room_id, description)
VALUES
('Konferencë Biznesi', 'ABC Corporation', '2024-07-15', '09:00:00', 1, 'Konferencë mbi inovacionin dhe teknologjinë.'),
('Dasmë', 'Familja Krasniqi', '2024-08-20', '18:00:00', 2, 'Ceremonia martesore dhe darka festive.'),
('Workshop Marketingu', 'Marketing Group', '2024-09-10', '10:30:00', 3, 'Workshop për marketing dixhital dhe strategji.'),
('Ekspozitë Arti', 'Galeria Art Prishtina', '2024-10-05', '15:00:00', 4, 'Ekspozitë pikturash dhe veprash artistike.'),
('Seminar Mjekësor', 'QKUK', '2024-11-12', '08:00:00', 5, 'Seminar për zhvillimet e fundit në mjekësi.');

-- Insert në tabelën ReservationHistory
INSERT INTO ReservationHistory (reservation_id, customer_id, change_date, old_status, new_status)
VALUES
(1, 1, CURRENT_TIMESTAMP, 'Pending', 'Confirmed'),
(2, 2, CURRENT_TIMESTAMP, 'Pending', 'Cancelled'),
(3, 3, CURRENT_TIMESTAMP, 'Confirmed', 'Cancelled'),
(4, 4, CURRENT_TIMESTAMP, 'Pending', 'Confirmed'),
(5, 5, CURRENT_TIMESTAMP, 'Confirmed', 'Completed');

-- Insert në tabelën Notification
INSERT INTO Notification (user_id, message, created_at, is_read)
VALUES
(1, 'Rezervimi juaj është konfirmuar me sukses!', CURRENT_TIMESTAMP, FALSE),
(2, 'Pagesa juaj është ende në pritje.', CURRENT_TIMESTAMP, FALSE),
(3, 'Mirë se vini në sistemin tonë të rezervimeve!', CURRENT_TIMESTAMP, TRUE),
(4, 'Ulje speciale për ju këtë javë!', CURRENT_TIMESTAMP, FALSE),
(5, 'Rezervimi juaj u anulua.', CURRENT_TIMESTAMP, FALSE);

                """;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
