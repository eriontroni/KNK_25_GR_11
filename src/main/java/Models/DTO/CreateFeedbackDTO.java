package Models.DTO;

/*CREATE TABLE Feedback (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES Customer(id) ON DELETE CASCADE,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);*/


import java.util.Date;

public class CreateFeedbackDTO {
    private int customer_id;
    private int reservation_id;
    private int rating;
    private String text;
    private Date created_at;

    public CreateFeedbackDTO(int customer_id, int reservation_id, int rating, String text, Date created_at){
        this.customer_id=customer_id;
        this.reservation_id=reservation_id;
        this.rating=rating;
        this.text=text;
        this.created_at=created_at;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRating() { return rating; }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
