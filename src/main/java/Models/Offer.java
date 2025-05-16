package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Offer {
    private int id;
    private String title;
    private String description;
    private double discountPercentage;
    private Date startDate;
    private Date endDate;

    // Constructor privat si te RoomImage
    private Offer(int id, String title, String description, double discountPercentage, Date startDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // getInstance(ResultSet) identik si te RoomImage
    public static Offer getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        double discountPercentage = resultSet.getDouble("discount_percentage");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");

        return new Offer(id, title, description, discountPercentage, startDate, endDate);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
