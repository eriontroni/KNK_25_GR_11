package repository;

import Models.Offer;
import Models.DTO.CreateOfferDTO;
import Models.DTO.UpdateOfferDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferRepository {

    private List<Offer> offers = new ArrayList<>();
    private int nextId = 1;

    public void create(CreateOfferDTO dto) {
        Offer offer = new Offer(
                nextId++,
                dto.getTitle(),
                dto.getDescription(),
                dto.getDiscountPercentage(),
                dto.getStartDate(),
                dto.getEndDate()
        );
        offers.add(offer);
    }

    public Offer findById(int id) {
        return offers.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Offer> findAll() {
        return new ArrayList<>(offers);
    }

    public void update(UpdateOfferDTO dto) {
        for (Offer offer : offers) {
            if (offer.getId() == dto.getId()) {
                offer.setTitle(dto.getTitle());
                offer.setDescription(dto.getDescription());
                offer.setDiscountPercentage(dto.getDiscountPercentage());
                offer.setStartDate(dto.getStartDate());
                offer.setEndDate(dto.getEndDate());
                break;
            }
        }
    }

    public void delete(int id) {
        offers.removeIf(o -> o.getId() == id);
    }
}
