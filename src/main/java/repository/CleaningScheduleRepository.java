package repository;

import Models.DTO.CreateCleaningScheduleDTO;
import Models.DTO.UpdateCleaningScheduleDTO;
import Models.CleaningSchedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CleaningScheduleRepository extends BaseRepository<CleaningSchedule, CreateCleaningScheduleDTO, UpdateCleaningScheduleDTO>{
    public CleaningScheduleRepository() { super("CleaningSchedule");}

    @Override
    public CleaningSchedule fromResultSet(ResultSet res) {
        try {
            return CleaningSchedule.getInstance(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CleaningSchedule create(CreateCleaningScheduleDTO cleaningschedule){
        String query = """
                INSERT INTO CleaningSchedule (room_id, employee_id, scheduled_date, status)
                VALUES (?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, cleaningschedule.getRoom_id());
            pstm.setInt(2, cleaningschedule.getEmployee_id());
            pstm.setDate(3, new java.sql.Date(cleaningschedule.getScheduled_date().getTime()));
            pstm.setString(4,cleaningschedule.getStatus());

            pstm.execute();
            ResultSet result = pstm.getGeneratedKeys();

            if(result.next()){
                int id = result.getInt(1);
                return this.getById(id);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CleaningSchedule update(UpdateCleaningScheduleDTO cleaningschedule) {
        String query = """
                UPDATE CleaningSchedule
                SET employee_id = ?, scheduled_date = ?, status = ?
                WHERE id = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, cleaningschedule.getEmployee_id());
            pstm.setDate(2, new java.sql.Date(cleaningschedule.getScheduled_date().getTime()));
            pstm.setString(3, cleaningschedule.getStatus());
            pstm.setInt(4, cleaningschedule.getId());
            int updatedRecord = pstm.executeUpdate();
            if (updatedRecord == 1){
                return this.getById(cleaningschedule.getId());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }
}

