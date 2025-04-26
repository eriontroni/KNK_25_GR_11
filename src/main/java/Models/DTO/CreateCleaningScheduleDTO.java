package Models.DTO;

import java.time.LocalDateTime;

/*CREATE TABLE CleaningSchedule (
    id SERIAL PRIMARY KEY,
    room_id INT REFERENCES Room(id) ON DELETE CASCADE,
    employee_id INT REFERENCES Employee(id),
    scheduled_date DATE NOT NULL,
    status VARCHAR(50) CHECK (status IN ('Scheduled', 'Completed', 'Missed')) DEFAULT 'Scheduled'
);*/

public class CreateCleaningScheduleDTO {
    private int room_id;
    private int employee_id;
    private LocalDateTime scheduled_date;
    private String status;

    public CreateCleaningScheduleDTO(int room_id, int employee_id, LocalDateTime scheduled_date, String status){
        this.room_id=room_id;
        this.employee_id=employee_id;
        this.scheduled_date=scheduled_date;
        this.status=status;
    }

    public int getRoom_id(){
        return room_id;
    }

    public void setRoom_id(int room_id){
        this.room_id=room_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDateTime getScheduled_date() {
        return scheduled_date;
    }

    public void setScheduled_date(LocalDateTime scheduled_date) {
        this.scheduled_date = scheduled_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
