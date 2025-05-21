package KrijimiTabelave;

import DataBase.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/*PER TE GJITHE:
* SEPARI KRIJONI 5 USERA dhe 5 EMPLOYEES, me ne fund bejeni run kete query
* */
public class DataLog {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        String query = """
-- Insert në tabelën RoomType
INSERT INTO RoomType (id,name, description, capacity, price_per_night)
VALUES
(1,'Single', 'Dhomë me një krevat single', 1, 30.00),
(2,'Double', 'Dhomë me një krevat dopio', 2, 55.00),
(3,'Suite', 'Dhomë suite luksoze', 4, 120.00),
(4,'Family', 'Dhomë e madhe familjare', 5, 95.00),
(5,'Economy', 'Dhomë ekonomike me facilitete bazë', 1, 25.00);

-- Insert në tabelën Room (sigurohet që type_id është i saktë nga RoomType)
INSERT INTO Room (id,room_number, type_id, is_available) VALUES
(1,'101', 1, TRUE),
(2,'102', 2, TRUE),
(3,'201', 3, FALSE),
(4,'202', 4, TRUE),
(5,'301', 5, TRUE);

-- Insert në tabelën RoomImage
INSERT INTO RoomImage (id,image_url,room_id)
VALUES
(1,'/images/img.png',5),
(2,'/images/img_1.png',4),
(3,'/images/img_2.png',3),
(4,'/images/img_3.png',2),
(5,'/images/img_4.png',1);

-- Insert në tabelën Offer
INSERT INTO Offer (id,title, description,code, discount_percentage, start_date, end_date)
VALUES
(1,'Oferta Pranverore', 'Ulje speciale për muajin Prill dhe Maj',123, 15.00, '2024-04-01', '2024-05-31'),
(2,'Fundjava Relax', 'Ulje 10% për fundjava',645, 10.00, '2024-06-01', '2024-08-31'),
(3,'Oferta për Çiftet', 'Ulje speciale 20% për çiftet',478, 20.00, '2024-02-01', '2024-12-31'),
(4,'Oferta Ditore', 'Ulje ditore për rezervime të minutës së fundit',986, 5.00, '2024-05-01', '2024-12-31'),
(5,'Oferta VIP', 'Ulje 25% për klientët VIP',357, 25.00, '2024-01-01', '2024-12-31');

-- Insert në tabelën Reservation (customer_id dhe room_id duhet të jenë ekzistuese nga tabela Customer dhe Room)
INSERT INTO Reservation (id,customer_id, room_id,offer_id, check_in_date, check_out_date, status, total_price)
VALUES
(1,1, 2, 2, '2024-06-10', '2024-06-12', 'Confirmed', 120.00),
(2,2, 3,NULL,'2024-07-15', '2024-07-20', 'Pending', 300.00),
(3,3, 1, 2, '2024-06-05', '2024-06-07', 'Cancelled', 70.00),
(4,4, 4,NULL, '2024-08-01', '2024-08-05', 'Confirmed', 400.00),
(5,5, 5,NULL, '2024-09-10', '2024-09-15', 'Pending', 125.00);

-- Insert në tabelën Event
INSERT INTO Event (id,event_name, organizer_name, event_date, event_time, room_id, description)
VALUES
(1,'Konferencë Biznesi', 'ABC Corporation', '2024-07-15', '09:00:00', 1, 'Konferencë mbi inovacionin dhe teknologjinë.'),
(2,'Dasmë', 'Familja Krasniqi', '2024-08-20', '18:00:00', 2, 'Ceremonia martesore dhe darka festive.'),
(3,'Workshop Marketingu', 'Marketing Group', '2024-09-10', '10:30:00', 3, 'Workshop për marketing dixhital dhe strategji.'),
(4,'Ekspozitë Arti', 'Galeria Art Prishtina', '2024-10-05', '15:00:00', 4, 'Ekspozitë pikturash dhe veprash artistike.'),
(5,'Seminar Mjekësor', 'QKUK', '2024-11-12', '08:00:00', 5, 'Seminar për zhvillimet e fundit në mjekësi.');


-- Insert në tabelën Maintenance
INSERT INTO Maintenance (id,room_id, reported_by, description, status, reported_at)
VALUES
(1,1, 4, 'Problem me kondicionerin, nuk ftoh mirë.', 'Pending', CURRENT_TIMESTAMP),
(2,2, 5, 'Lavaman i bllokuar.', 'In Progress', CURRENT_TIMESTAMP),
(3,3, 4, 'Ndriçimi në banjë nuk funksionon.', 'Completed', CURRENT_TIMESTAMP),
(4,4, 5, 'Dera e ballkonit nuk mbyllet mirë.', 'Pending', CURRENT_TIMESTAMP),
(5,5, 4, 'Probleme me televizorin.', 'In Progress', CURRENT_TIMESTAMP);

-- Insert në tabelën Payment
INSERT INTO Payment (id,reservation_id, amount, payment_method, payment_status)
VALUES
(1,1, 120.00, 'Credit Card', 'Completed'),
(2,2, 300.00, 'PayPal', 'Pending'),
(3,3, 70.00, 'Cash', 'Failed'),
(4,4, 400.00, 'Debit Card', 'Completed'),
(5,5, 125.00, 'Credit Card', 'Completed');

-- Insert në tabelën RoomService
INSERT INTO RoomService (id,reservation_id, service_name, price, requested_at)
VALUES
(1,1, 'Mëngjes në dhomë', 15.00, CURRENT_TIMESTAMP),
(2,2, 'Larje Rrobash', 10.00, CURRENT_TIMESTAMP),
(3,3, 'Masazh SPA', 50.00, CURRENT_TIMESTAMP),
(4,4, 'Darka Speciale', 30.00, CURRENT_TIMESTAMP),
(5,5, 'Pastrim Ekstra', 20.00, CURRENT_TIMESTAMP);

-- Insert në tabelën CleaningSchedule
INSERT INTO CleaningSchedule (id,room_id, employee_id, scheduled_date, status)
VALUES
(1,1, 4, '2024-06-11', 'Scheduled'),
(2,2, 5, '2024-07-16', 'Completed'),
(3,3, 4, '2024-06-06', 'Missed'),
(4,4, 5, '2024-08-03', 'Scheduled'),
(5,5, 4, '2024-09-12', 'Scheduled');

-- Insert në tabelën Feedback
INSERT INTO Feedback (id,customer_id, reservation_id, rating, comment, created_at)
VALUES
(1,1, 1, 5, 'Shërbim shumë i mirë, ambient i këndshëm.', CURRENT_TIMESTAMP),
(2,2, 2, 4, 'Dhoma ishte e pastër dhe e rregullt.', CURRENT_TIMESTAMP),
(3,3, 3, 2, 'Pata probleme me rezervimin tim.', CURRENT_TIMESTAMP),
(4,4, 4, 3, 'Mirë, por mund të përmirësohet.', CURRENT_TIMESTAMP),
(5,5, 5, 5, 'Fantastike! Do ta rekomandoja!', CURRENT_TIMESTAMP);

-- Insert në tabelën ReservationDiscount
INSERT INTO ReservationDiscount (id,reservation_id, offer_id)
VALUES
(1,1, 1),
(2,2, 2),
(3,3, 3),
(4,4, 4),
(5,5, 5);


-- Insert në tabelën ReservationHistory
INSERT INTO ReservationHistory (id,reservation_id, customer_id, change_date, old_status, new_status)
VALUES
(1,1, 1, CURRENT_TIMESTAMP, 'Pending', 'Confirmed'),
(2,2, 2, CURRENT_TIMESTAMP, 'Pending', 'Cancelled'),
(3,3, 3, CURRENT_TIMESTAMP, 'Confirmed', 'Cancelled'),
(4,4, 4, CURRENT_TIMESTAMP, 'Pending', 'Confirmed'),
(5,5, 5, CURRENT_TIMESTAMP, 'Confirmed', 'Completed');

-- Insert në tabelën Notification
INSERT INTO Notification (id,user_id, message, created_at, is_read)
VALUES
(1,1, 'Rezervimi juaj është konfirmuar me sukses!', CURRENT_TIMESTAMP, FALSE),
(2,2, 'Pagesa juaj është ende në pritje.', CURRENT_TIMESTAMP, FALSE),
(3,3, 'Mirë se vini në sistemin tonë të rezervimeve!', CURRENT_TIMESTAMP, TRUE),
(4,4, 'Ulje speciale për ju këtë javë!', CURRENT_TIMESTAMP, FALSE),
(5,5, 'Rezervimi juaj u anulua.', CURRENT_TIMESTAMP, FALSE);

                """;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
