package Models.DTO;

import java.math.BigDecimal;

public class CreatePaymentDTO {

//    CREATE TABLE Payment (
//            id SERIAL PRIMARY KEY,
//            reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
//    amount DECIMAL(10,2) NOT NULL,
//    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'Debit Card', 'Cash', 'PayPal')) NOT NULL,
//    payment_status VARCHAR(50) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending'

        private int reservation_id;
        private BigDecimal amount;
        private String payment_method;
        private String payment_status;

        public CreatePaymentDTO(int reservation_id, BigDecimal amount, String payment_method, String payment_status) {
            this.reservation_id = reservation_id;
            this.amount = amount;
            this.payment_method = payment_method;
            this.payment_status = payment_status;
        }

        public int getReservation_id() { return reservation_id; }

        public void setReservation_id(int reservation_id) { this.reservation_id = reservation_id; }

        public BigDecimal getAmount() { return amount; }

        public void setAmount(BigDecimal amount) { this.amount = amount; }

        public String getPayment_method() { return payment_method; }

        public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

        public String getPayment_status() { return payment_status; }

        public void setPayment_status(String payment_status) { this.payment_status = payment_status; }
    }

