package Models.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateDiscountDTO {

//    CREATE TABLE Discount (
//            id SERIAL PRIMARY KEY,
//            code VARCHAR(50) UNIQUE NOT NULL,
//    description TEXT,
//    percentage DECIMAL(5,2) CHECK (percentage > 0 AND percentage <= 100),
//    valid_from DATE NOT NULL,
//    valid_to DATE NOT NULL
//);

    private String code;
    private String description;
    private BigDecimal percentage;
    private LocalDate valid_from;
    private LocalDate valid_to;

    public CreateDiscountDTO(int id, String code, String description, BigDecimal percentage, LocalDate valid_from, LocalDate valid_to) {
        this.code = code;
        this.description = description;
        this.percentage = percentage;
        this.valid_from = valid_from;
        this.valid_to = valid_to;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public LocalDate getValid_from() {
        return valid_from;
    }

    public void setValid_from(LocalDate valid_from) {
        this.valid_from = valid_from;
    }

    public LocalDate getValid_to() {
        return valid_to;
    }

    public void setValid_to(LocalDate valid_to) {
        this.valid_to = valid_to;
    }
}
