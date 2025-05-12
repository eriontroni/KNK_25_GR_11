package repository;

import DataBase.DBConnector;
import Models.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
// cdo komunikim me baze te te dhenave, behet permes repositories

abstract class BaseRepository<Model, CreateModelDto, UpdateModelDto> {
    protected Connection connection;
    private String tableName;

    public BaseRepository(String tableName) {
        this.connection = DBConnector.getConnection();
        this.tableName = tableName;
    }

    public static String convertLocalDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    abstract Model fromResultSet(ResultSet res);

    public Model getById(int id){
        String query = "SELECT * FROM " + this.tableName + " WHERE id = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return this.fromResultSet(res);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Model> getAll(){
        ArrayList<Model> models = new ArrayList<>();
        String query = "SELECT * FROM " + this.tableName;
        try{
            Statement statement = this.connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                models.add(this.fromResultSet(res));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return models;
    }

    abstract Model create(CreateModelDto createModelDto);

    abstract Model update(UpdateModelDto updateModelDto);

//    abstract boolean delete(int id);


    public boolean delete(int id){
        String query = "DELETE FROM "+this.tableName +" WHERE ID = ?";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}