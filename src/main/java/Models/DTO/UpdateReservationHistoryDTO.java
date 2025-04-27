package Models.DTO;

public class UpdateReservationHistoryDTO {
    private String old_status;
    private String new_status;

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
