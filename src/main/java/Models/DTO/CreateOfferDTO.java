package Models.DTO;

import java.sql.Date;

public class CreateOfferDTO {
    private String title;
    private String description;
    private double discount_percentage;
    private Date start_date;
    private Date end_date;

    public CreateOfferDTO(String title, String description, double discount_percentage, Date start_date, Date end_date) {
        this.title = title;
        this.description = description;
        this.discount_percentage = discount_percentage;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
