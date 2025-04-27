package Models.DTO;

/*
  UPDATE te RoomService bëhet përmes fushës 'id' dhe jo 'reservation_id'.

  - 'id' është PRIMARY KEY dhe është unik për çdo shërbim.
  - 'reservation_id' është FOREIGN KEY që lidh shërbimin me rezervimin, por nuk është unik.
    (Një rezervim mund të ketë shumë shërbime.)

  Prandaj, kur duam të bëjmë update të një shërbimi të caktuar,
  përdorim 'id' për ta identifikuar saktësisht rreshtin që duhet përditësuar.
*/

public class UpdateRoomServiceDTO {
    private int id;
    private String serviceName;
    private double price;

    public UpdateRoomServiceDTO(int id, String serviceName, double price){
        this.id=id;
        this.serviceName=serviceName;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
