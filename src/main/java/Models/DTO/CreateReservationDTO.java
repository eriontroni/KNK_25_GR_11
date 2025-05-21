package Models.DTO;

import java.sql.Date;

//-- 7. Reservation -Natyra
//CREATE TABLE Reservation (
//        id INT PRIMARY KEY,
//        customer_id INT REFERENCES Users(id) ON DELETE CASCADE,
//room_id INT REFERENCES Room(id) ON DELETE CASCADE,
//offer_id INT REFERENCES Offer(id) ON DELETE SET NULL,
//check_in_date DATE NOT NULL,
//check_out_date DATE NOT NULL,
//status VARCHAR(50) CHECK (status IN ('Pending', 'Confirmed', 'Cancelled')) DEFAULT 'Pending',
//total_price DECIMAL(10,2) NOT NULL
//);

public class CreateReservationDTO {
    private int id; //perdoret psh per update ose view
    private int customerId;
    private int roomId;
    private Integer offerId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalPrice;

    //konstruktor per me kriju nje rezervim
    public CreateReservationDTO(int id, int customerId, int roomId, Integer offerId, Date checkInDate, Date checkOutDate, String status, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.offerId = offerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // getter per fushat
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Integer getOfferId() { return offerId;}

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    // formatimi i dates se hyrjes si string
    public String getFormattedCheckInDate() {
        return checkInDate.toString();  // "yyyy-mm-dd"
    }

    // data e daljes si string
    public String getFormattedCheckOutDate() {
        return checkOutDate.toString();
    }

    // kalimi i java.sql.Date ne LocalDate (nese esht e nevojshme per ndonje rast tjeter)
    public java.time.LocalDate getLocalCheckInDate() {
        return checkInDate.toLocalDate();
    }

    public java.time.LocalDate getLocalCheckOutDate() {
        return checkOutDate.toLocalDate();
    }

}
