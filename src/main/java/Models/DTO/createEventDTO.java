package Models.DTO;

import java.util.Date;
import java.time.LocalTime;

//CREATE TABLE Event (
  //      id SERIAL PRIMARY KEY,
     //   event_name VARCHAR(255) NOT NULL,
//organizer_name VARCHAR(255) NOT NULL,
//event_date DATE NOT NULL,
//event_time TIME NOT NULL,
//room_id INT REFERENCES Room(id) ON DELETE SET NULL,
//description TEXT
//);
public class createEventDTO {
    private String event_name;
    private String organizer_name;
    private Date event_date;
    private LocalTime event_time;
    private Integer room_id;
    private String description;

    public createEventDTO(String event_name, String organizer_name, Date event_date, LocalTime event_time, Integer room_id, String description){
        this.event_name = event_name;
        this.organizer_name = organizer_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.room_id = room_id;
        this.description = description;
    }
    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }
    public String getOrganizer_name() {
        return organizer_name;
    }

    public void setOrganizer_name(String organizer_name) {
        this.organizer_name = organizer_name;
    }
    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }
    public LocalTime getEvent_time() {
        return event_time;
    }

    public void setEvent_time(LocalTime event_time) {
        this.event_time = event_time;
    }
    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
