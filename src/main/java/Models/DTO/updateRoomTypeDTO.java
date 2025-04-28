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

public class updateRoomTypeDTO {

    private String description;
    private int capacity;
    private float price_per_night;

    public updateRoomTypeDTO(String name, String description, int capacity, float price_per_night) {

        this.description = description;//Mund të ndryshosh përshkrimin nëse ndryshon informacioni.
        this.capacity = capacity;//	Mund të ndryshosh kapacitetin (p.sh. nga 2 persona në 3 persona).
        this.price_per_night = price_per_night;//	Mund të ndryshosh çmimin për natë në varësi të ofertave, sezonit etj.
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
}

