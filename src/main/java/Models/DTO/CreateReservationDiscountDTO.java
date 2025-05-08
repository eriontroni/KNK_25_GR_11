package Models.DTO;

public class CreateReservationDiscountDTO {
    private int reservationId;
    private int discountId;

    public CreateReservationDiscountDTO() {
    }

    public CreateReservationDiscountDTO(int reservationId, int discountId) {
        this.reservationId = reservationId;
        this.discountId = discountId;
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

