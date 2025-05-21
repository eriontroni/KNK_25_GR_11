package Services;

import Models.Offer;
import Models.RoomType;
import repository.OfferRepository;
import repository.RoomTypeRepository;

public class OfferService {

    public static Boolean checkCode(int offerID, String UserCode) {
        OfferRepository or = new OfferRepository();
        Offer offer = or.getById(offerID);
        return offer.getCode().equals(UserCode);
    }

    public static double getDiscountedPrice(double cmimi,double zbritja){
        return cmimi - cmimi*zbritja;
    }

    public static double getPriceByRoom(int type_id){
        RoomTypeRepository rt = new RoomTypeRepository();
        RoomType type = rt.getById(type_id);
        return type.getPrice_per_night();
    }
}

