package Models.DTO;

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
