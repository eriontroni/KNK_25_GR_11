package repository;

import Models.ReservationDiscount;
import Models.DTO.CreateReservationDiscountDTO;
import Models.DTO.UpdateReservationDiscountDTO;

import java.util.List;

public interface ReservationDiscountRepository {

    void create(CreateReservationDiscountDTO dto);

    ReservationDiscount findById(int id);

    List<ReservationDiscount> findAll();

    void update(UpdateReservationDiscountDTO dto);

    void delete(int id);
}
