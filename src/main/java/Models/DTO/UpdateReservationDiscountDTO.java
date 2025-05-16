package Models.DTO;

public class UpdateReservationDiscountDTO {
    private int id;
    private int reservation_id;
    private int discount_id;

    UpdateReservationDiscountDTO(int reservation_id, int discount_id, int id) {
        this.reservation_id = reservation_id;
        this.discount_id = discount_id;
        this.id = id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
