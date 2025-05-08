package Models.DTO;
import java.time.LocalDate;

public class CreateOfferDTO {
    private String title;
    private String description;
    private double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public CreateOfferDTO() {
    }

    public CreateOfferDTO(String title, String description, double discountPercentage, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
