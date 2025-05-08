package Models;

public class ReservationDiscount {
    private int id;
    private int reservationId;
    private int discountId;

    public ReservationDiscount() {
    }

    public ReservationDiscount(int id, int reservationId, int discountId) {
        this.id = id;
        this.reservationId = reservationId;
        this.discountId = discountId;
    }

    // Getter dhe Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
