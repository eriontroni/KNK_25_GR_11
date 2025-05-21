package Services;

import Models.Room;
import Models.RoomType;
import repository.RoomTypeRepository;

public class ReservationService {
    //getType from Room:
    public static String getTypeFromRoom(Room room){
        int typeID = room.getTypeId();
        RoomTypeRepository rtr = new RoomTypeRepository();
        RoomType rt = rtr.getById(typeID);
        return rt.getName();
    }
    public static double getPriceFromRoom(Room room){
        int typeID = room.getTypeId();
        RoomTypeRepository rtr = new RoomTypeRepository();
        RoomType rt = rtr.getById(typeID);
        return rt.getPrice_per_night();
    }
}
