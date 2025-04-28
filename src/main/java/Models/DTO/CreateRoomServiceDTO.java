package Models.DTO;

import java.math.BigDecimal;
import java.sql.Date;

/*CREATE TABLE RoomService (
    id SERIAL PRIMARY KEY,
    reservation_id INT REFERENCES Reservation(id) ON DELETE CASCADE,
    service_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);*/

public class CreateRoomServiceDTO {
    private int reservationId;
    private String serviceName;
    private BigDecimal price;
    private Date requested_at;

    public CreateRoomServiceDTO(int reservationId, String serviceName, BigDecimal price, Date requested_at){
        this.reservationId=reservationId;
        this.serviceName=serviceName;
        this.price=price;
        this.requested_at=requested_at;
    }

    public int getReservationId(){
        return reservationId;
    }

    public void setReservationId(int reservationId){
        this.reservationId=reservationId;
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceName(String serviceName){
        this.serviceName=serviceName;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price=price;
    }

    public Date getRequested_at(){
        return requested_at;
    }

    public void setRequested_at(Date requested_at){
        this.requested_at=requested_at;
    }
}
