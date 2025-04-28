package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CleaningSchedule {
    private int id;
    private int room_id;
    private int employee_id;
    private Date scheduled_date ;
    private String status;

    CleaningSchedule(int id, int room_id, int employee_id, Date scheduled_date, String status){
        this.id=id;
        this.room_id=room_id;
        this.employee_id=employee_id;
        this.scheduled_date=scheduled_date;
        this.status=status;
    }

    public static CleaningSchedule getInstance(ResultSet resultSet) throws SQLException{
        int id= resultSet.getInt("id");
        int room_id=resultSet.getInt("room_id");
        int employee_id=resultSet.getInt("employee_id");
        Date scheduled_date=resultSet.getDate("scheduled_date");
        String status= resultSet.getString("status");

        return new CleaningSchedule(id,room_id,employee_id,scheduled_date,status);
    }

    public int getId() { return id; }

    public int getRoom_id() { return room_id; }

    public int getEmployee_id() { return employee_id; }

    public Date getScheduled_date() { return scheduled_date; }

    public String getStatus() { return status; }
}
