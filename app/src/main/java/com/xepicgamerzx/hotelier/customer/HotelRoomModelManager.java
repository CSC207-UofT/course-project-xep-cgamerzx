package com.xepicgamerzx.hotelier.customer;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.RoomBedsCrossManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelRoomModelManager {

    public static List<CustomerHotelRoomsModel> getHotelViewModelList(List<HotelRoom> hotelRooms, Application application) {
        List<CustomerHotelRoomsModel> hotelRoomsView = new ArrayList<>();
        RoomBedsCrossManager roomBedsCrossManager = RoomBedsCrossManager.getManager(application);

        for (HotelRoom hotelRoom : hotelRooms) {
            String bedTypes = "";
            String availability = UnixEpochDateConverter.epochToReadable(
                    hotelRoom.getStartAvailability(), hotelRoom.getEndAvailability()
            );
            List<Bed> beds = roomBedsCrossManager.getRelated(hotelRoom);

            for(Bed bed : beds) {
                // unique id is the bed type/size.
                bedTypes += bed.getUniqueId().toUpperCase(Locale.ROOT) + ", ";
            }

            hotelRoomsView.add(new CustomerHotelRoomsModel(
                    beds.size(),
                    bedTypes,
                    hotelRoom.getCapacity(),
                    hotelRoom.getPrice(),
                    availability,
                    hotelRoom
            ));
        }

        return hotelRoomsView;
    }

}
