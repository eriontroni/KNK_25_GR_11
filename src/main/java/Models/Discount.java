package Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {
    private int id;
    private String code;
    private String description;
    private BigDecimal percentage;
    private Date validFrom;
    private Date validTo;

    private Discount(int id, String code, String description, BigDecimal percentage, Date validFrom, Date validTo) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public static Discount getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String code = rs.getString("code");
        String description = rs.getString("description");
        BigDecimal percentage = rs.getBigDecimal("percentage");
        Date validFrom = rs.getDate("valid_from");
        Date validTo = rs.getDate("valid_to");

        return new Discount(id, code, description, percentage, validFrom, validTo);
    }

    public int getId() { return id; }

    public String getCode() { return code; }

    public String getDescription() { return description; }

    public BigDecimal getPercentage() { return percentage; }

    public Date getValidFrom() { return validFrom; }

    public Date getValidTo() { return validTo; }
}
