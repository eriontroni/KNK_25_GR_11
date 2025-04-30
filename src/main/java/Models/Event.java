package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class Event {
    private int id;
    private String event_name;
    private String organizer_name;
    private Date event_date;
    private LocalTime event_time;
    private Integer room_id;
    private String description;


    private Event(int id,String event_name, String organizer_name, Date event_date, LocalTime event_time, Integer room_id, String description){
        this.id = id;
        this.event_name = event_name;
        this.organizer_name = organizer_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.room_id = room_id;
        this.description = description;
    }
    public static Event getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String event_name = resultSet.getString("event_name");
        String organizer_name = resultSet.getString("organizer_name");
        Date event_date = resultSet.getDate("event_date");
        LocalTime event_time = resultSet.getObject("event_time", LocalTime.class);
        Integer room_id = resultSet.getObject("room_id") != null ? resultSet.getInt("room_id") : null;
        String description = resultSet.getString("description");

        return new Event(id,event_name, organizer_name, event_date, event_time, room_id, description);}

        public String getEvent_name() {return event_name;}

        public String getOrganizer_name() {return organizer_name;}

        public Date getEvent_date() {return event_date;}

        public LocalTime getEvent_time() {return event_time;}

        public Integer getRoom_id() {return room_id;}

        public String getDescription() {return description;}
    }

