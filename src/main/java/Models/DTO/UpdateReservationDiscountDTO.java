package Models.DTO;

public class UpdateReservationDiscountDTO {
    private int id;
    private int reservationId;
    private int discountId;

    public UpdateReservationDiscountDTO() {
    }

    public UpdateReservationDiscountDTO(int id, int reservationId, int discountId) {
        this.id = id;
        this.reservationId = reservationId;
        this.discountId = discountId;
    }

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
