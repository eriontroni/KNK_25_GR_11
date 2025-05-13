package repository;

import Models.DTO.CreateCleaningScheduleDTO;
import Models.DTO.UpdateCleaningScheduleDTO;
import Models.CleaningSchedule;
import Models.DTO.UpdateRoomImageDTO;
import Models.ReservationHistory;
import Models.RoomImage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CleaningScheduleRepository extends BaseRepository<CleaningSchedule, CreateCleaningScheduleDTO, UpdateCleaningScheduleDTO>{
    public CleaningScheduleRepository(String tableName) { super("CleaningSchedule");}

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
    CleaningSchedule create(CreateCleaningScheduleDTO CleaningSchedule){
        String query = """
                INSERT INTO CleaningSchedule (room_id, employee_id, scheduled_date, status)
                VALUES (?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS
            );
            pstm.setInt(1, CleaningSchedule.getRoom_id());
            pstm.setInt(2, CleaningSchedule.getEmployee_id());
            pstm.setDate(3,CleaningSchedule.getScheduled_date() );
            pstm.setString(4,CleaningSchedule.getStatus());

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
    CleaningSchedule update(UpdateCleaningScheduleDTO CleaningSchedule) {
        String query = """
                UPDATE CleaningSchedule
                SET employee_id = ?, scheduled_date = ?, status = ?
                WHERE id = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, CleaningSchedule.getEmployee_id());
            pstm.setDate(2, CleaningSchedule.getScheduled_date());
            pstm.setString(3, CleaningSchedule.getStatus());
            pstm.setInt(4, CleaningSchedule.getId());
            int updatedRecord = pstm.executeUpdate();
            if (updatedRecord == 1){
                return this.getById(CleaningSchedule.getId());
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

