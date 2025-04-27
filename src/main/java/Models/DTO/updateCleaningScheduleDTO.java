package Models.DTO;

import java.time.LocalDateTime;

public class UpdateCleaningScheduleDTO {
    private int id;
    private int employee_id;
    private LocalDateTime scheduled_date;
    private String status;

    public UpdateCleaningScheduleDTO(int id, int employee_id, LocalDateTime scheduled_date, String status){
        this.id=id;
        this.employee_id=employee_id;
        this.scheduled_date=scheduled_date;
        this.status=status;
    }

    public int getId() { return id; }

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

