package com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity;

import android.app.Application;

import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomBedsCrossManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelRoomModelManager {
    public static List<CustomerHotelRoomsModel> getHotelViewModelList(List<HotelRoom> hotelRooms, Application application) {
        List<CustomerHotelRoomsModel> hotelRoomsView = new ArrayList<>();
        RoomBedsCrossManager roomBedsCrossManager = RoomBedsCrossManager.getManager(application);

        for (HotelRoom hotelRoom : hotelRooms) {
            StringBuilder bedTypes = new StringBuilder();
            String availability = UnixEpochDateConverter.epochToReadable(
                    hotelRoom.getStartAvailability(), hotelRoom.getEndAvailability()
            );
            List<Bed> beds = roomBedsCrossManager.getRelated(hotelRoom);

            for (Bed bed : beds) {
                // unique id is the bed type/size.
                bedTypes.append(bed.getUniqueId().toUpperCase(Locale.ROOT)).append(", ");
            }

            hotelRoomsView.add(new CustomerHotelRoomsModel(
                    beds.size(),
                    bedTypes.toString(),
                    hotelRoom.getCapacity(),
                    hotelRoom.getPrice(),
                    availability,
                    hotelRoom
            ));
        }

        return hotelRoomsView;
    }

    public static CustomerHotelRoomsAdapter getAdapterRooms(HotelViewModel hotelViewModel, Application application, @Nullable Long userStartDate, @Nullable Long userEndDate) {
        List<HotelRoom> rooms;

        if (hotelViewModel.getRooms() != null) {
            rooms = hotelViewModel.getRooms();
        } else if (userStartDate != null && userEndDate != null) {
            rooms = Manage.getManager(application).roomManager.getAvailableRooms(userStartDate, userEndDate, hotelViewModel.getHotel());
        } else {
            rooms = Manage.getManager(application).roomManager.getHotelRoomsInHotel(hotelViewModel.getHotel().hotelId);
        }
        return new CustomerHotelRoomsAdapter(getHotelViewModelList(rooms, application));
    }

}
