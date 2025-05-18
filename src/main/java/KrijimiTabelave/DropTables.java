package KrijimiTabelave;

import DataBase.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTables {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        //nese ndodh ndonje ndryshim ne tabela i fshijm dhe i fusim prap
        String query = """
                DROP TABLE IF EXISTS Notification CASCADE;
                DROP TABLE IF EXISTS ReservationHistory CASCADE;
                DROP TABLE IF EXISTS Event CASCADE;
                DROP TABLE IF EXISTS Offer CASCADE;
                DROP TABLE IF EXISTS Maintenance CASCADE;
                DROP TABLE IF EXISTS ReservationDiscount CASCADE;
                DROP TABLE IF EXISTS Discount CASCADE;
                DROP TABLE IF EXISTS RoomImage CASCADE;
                DROP TABLE IF EXISTS Feedback CASCADE;
                DROP TABLE IF EXISTS CleaningSchedule CASCADE;
                DROP TABLE IF EXISTS RoomService CASCADE;
                DROP TABLE IF EXISTS Payment CASCADE;
                DROP TABLE IF EXISTS Reservation CASCADE;
                DROP TABLE IF EXISTS Employee CASCADE;
                DROP TABLE IF EXISTS Users CASCADE;
                DROP TABLE IF EXISTS Customer CASCADE;
                DROP TABLE IF EXISTS Room CASCADE;
                DROP TABLE IF EXISTS RoomType CASCADE;
                """;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
