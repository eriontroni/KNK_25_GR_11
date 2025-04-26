package Models.DTO;

//CREATE TABLE RoomType (
  //      id SERIAL PRIMARY KEY,
    //    name VARCHAR(100) NOT NULL,
//description TEXT,
//capacity INT CHECK (capacity > 0),
//price_per_night DECIMAL(10,2) CHECK (price_per_night >= 0),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);


import java.time.LocalDateTime;

public class createRoomTypeDTO {
private String name;
private String description;
private int capacity;
private float price_per_night;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;

public createRoomTypeDTO(String name, String description, int capacity,float price_per_night, LocalDateTime createdAt, LocalDateTime updatedAt){
    this.name = name;
    this.description = description;
    this.capacity = capacity;
    this.price_per_night = price_per_night;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(float price_per_night) {
        this.price_per_night = price_per_night;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}
