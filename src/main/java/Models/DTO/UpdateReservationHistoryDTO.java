package Models.DTO;

public class UpdateReservationHistoryDTO {
    private int id;
    private String old_status;
    private String new_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UpdateReservationHistoryDTO(String old_status, String new_status) {
        this.old_status = old_status;
        this.new_status = new_status;
    }

    public String getOld_status() {
        return old_status;
    }

    public void setOld_status(String old_status) {
        this.old_status = old_status;
    }

    public String getNew_status() {
        return new_status;
    }

    public void setNew_status(String new_status) {
        this.new_status = new_status;
    }
}
