package Models.DTO;

import java.time.LocalDateTime;

//CREATE TABLE ReservationHistory (
//        id SERIAL PRIMARY KEY,
//        reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
//customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
//change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//old_status VARCHAR(50),
//new_status VARCHAR(50)
//);
public class CreateReservationHistoryDTO {
    private int reservation_id;
    private int customer_id;
    private LocalDateTime change_date;
    private String old_status;
    private String new_status;

    public CreateReservationHistoryDTO(int reservation_id, int customer_id, LocalDateTime change_date, String old_status, String new_status) {
        this.reservation_id = reservation_id;
        this.customer_id = customer_id;
        this.change_date = change_date;
        this.old_status = old_status;
        this.new_status = new_status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDateTime getChange_date() {
        return change_date;
    }

    public void setChange_date(LocalDateTime change_date) {
        this.change_date = change_date;
    }

    public String getOld_status() {
        return old_status;
    }

    public void setOld_status(String old_status) {
        this.old_status = old_status;
    }

    public String getNew_status() {
        return new_status;
    }

    public void setNew_status(String new_status) {
        this.new_status = new_status;
    }
}
