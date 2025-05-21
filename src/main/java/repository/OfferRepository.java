package repository;

import Models.DTO.CreateOfferDTO;
import Models.DTO.UpdateOfferDTO;
import Models.Offer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OfferRepository extends BaseRepository<Offer, CreateOfferDTO, UpdateOfferDTO> {

    public OfferRepository() {
        super("Offer");
    }

    @Override
    public Offer fromResultSet(ResultSet res) {
        try {
            return Offer.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    Offer create(CreateOfferDTO offer) {
        String query = """
                INSERT INTO Offer (title, description,code, discount_percentage, start_date, end_date)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, offer.getTitle());
            pstm.setString(2, offer.getDescription());
            pstm.setString(3, offer.getCode());
            pstm.setDouble(4, offer.getDiscount_percentage());
            pstm.setDate(5, offer.getStart_date());
            pstm.setDate(6, offer.getEnd_date());
            pstm.execute();

            ResultSet result = pstm.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    Offer update(UpdateOfferDTO offer) {
        String query = """
                UPDATE Offer
                SET title = ?, description = ?, discount_percentage = ?,code = ?, start_date = ?, end_date = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, offer.getTitle());
            pstm.setString(2, offer.getDescription());
            pstm.setString(3, offer.getCode());
            pstm.setDouble(4, offer.getDiscount_percentage());
            pstm.setDate(5, offer.getStart_date());
            pstm.setDate(6, offer.getEnd_date());
            pstm.setInt(7, offer.getId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(offer.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * Ne Controller:
    * OfferRepository or = new OfferRepository();
    * Offer offer = or.getByTitle("**titulliOfertes**");
    *
    * */
    public Offer getByTitle(String title){
        String query = "SELECT * FROM Offer WHERE title = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, title);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return this.fromResultSet(res);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
