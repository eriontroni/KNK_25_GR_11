package Models.DTO;

public class updateEventDTO {
    private int id; // ID e eventit për ta identifikuar në përditësim
    private String event_name;
    private Integer room_id;
    private String description;

    public updateEventDTO(int id, String event_name, Integer room_id, String description) {
        this.id = id;
        this.event_name = event_name;
        this.room_id = room_id;
        this.description = description;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
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