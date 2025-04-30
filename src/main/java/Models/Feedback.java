package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Feedback {
    private int id;
    private int costumer_id;
    private int reservation_id;
    private int rating;
    private String comment;
    private Date created_at;

    public Feedback(int id, int costumer_id, int reservation_id, int rating, String comment, Date created_at){
        this.id=id;
        this.costumer_id=costumer_id;
        this.reservation_id=reservation_id;
        this.rating=rating;
        this.comment=comment;
        this.created_at=created_at;
    }

    public static Feedback getInstance(ResultSet resultSet) throws SQLException{
        int id=resultSet.getInt("id");
        int costumer_id=resultSet.getInt("costumer_id");
        int reservation_id=resultSet.getInt("reservation_id");
        int rating=resultSet.getInt("rating");
        String comment=resultSet.getString("comment");
        Date created_at=resultSet.getDate("created_at");

        return new Feedback(id, costumer_id,reservation_id, rating, comment, created_at);
    }

    public int getId() { return id; }

    public int getCostumer_id() { return costumer_id; }

    public int getReservation_id() { return reservation_id; }

    public int getRating() { return rating; }

    public String getComment() { return comment; }

    public Date getCreated_at() { return created_at; }
}
