package Models.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateDiscountDTO {
    private String description;
    private BigDecimal percentage;
    private Date valid_from;
    private Date valid_to;

    public UpdateDiscountDTO() {}

    public UpdateDiscountDTO(String description, BigDecimal percentage, Date valid_from, Date valid_to) {
        this.description = description;
        this.percentage = percentage;
        this.valid_from = valid_from;
        this.valid_to = valid_to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public Date getValid_from() {
        return valid_from;
    }

    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    public Date getValid_to() {
        return valid_to;
    }

    public void setValid_to(Date valid_to) {
        this.valid_to = valid_to;
    }
}
