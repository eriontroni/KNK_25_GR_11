package Repository;

import Models.ReservationDiscount;
import Models.DTO.CreateReservationDiscountDTO;
import Models.DTO.UpdateReservationDiscountDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationDiscountRepository {

    private List<ReservationDiscount> reservationDiscounts = new ArrayList<>();
    private int nextId = 1;

    public void create(CreateReservationDiscountDTO dto) {
        ReservationDiscount rd = new ReservationDiscount(
                nextId++,
                dto.getReservationId(),
                dto.getDiscountId()
        );
        reservationDiscounts.add(rd);
    }

    public ReservationDiscount findById(int id) {
        return reservationDiscounts.stream()
                .filter(rd -> rd.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<ReservationDiscount> findAll() {
        return new ArrayList<>(reservationDiscounts);
    }

    public void update(UpdateReservationDiscountDTO dto) {
        for (ReservationDiscount rd : reservationDiscounts) {
            if (rd.getId() == dto.getId()) {
                rd.setReservationId(dto.getReservationId());
                rd.setDiscountId(dto.getDiscountId());
                break;
            }
        }
    }

    public void delete(int id) {
        reservationDiscounts.removeIf(rd -> rd.getId() == id);
    }
}
