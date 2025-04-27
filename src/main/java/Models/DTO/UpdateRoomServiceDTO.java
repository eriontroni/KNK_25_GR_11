package Models.DTO;

public class UpdateRoomServiceDTO {
    private String serviceName;
    private double price;

    public UpdateRoomServiceDTO(String serviceName, double price){
        this.serviceName=serviceName;
        this.price=price;
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
