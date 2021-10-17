package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

// By Thomas

public class FileReadWriteRaw {

    private final File database = new File("HotelData.csv"); //database file

    /**
     * Read data from a csv file
     * Note: Format of each line in the file should be name;address;rooms
     * Data inside of address and rooms should be in the same order as its to string method
     * (if applicable) and separated with commas.
     * Dates should be in the form day month year separated by spaces.
     *
     * @return a list containing the data
     * @throws FileNotFoundException throw an exception if no file is found
     */
    public List<Object> readFile() throws IOException {
        List<Object> data = new ArrayList<>(); //all hotel data

        FileReader file = new FileReader(database);
        BufferedReader br = new BufferedReader(file);

        String headings = br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            Hashtable<String, Object> hotel = new Hashtable<>(); //mapping for data for each hotel
            Hashtable<String, String> address = new Hashtable<>(); //mapping for address data of each hotel
            Hashtable<String, Object> rooms = new Hashtable<>(); //mapping for room data of each hotel

            /*
            Read and parse data
             */
            String[] hotelData = line.split(";");
            String hotelName = hotelData[0];
            String[] hotelAddress = hotelData[1].split(",");
            String[] hotelRooms = hotelData[2].split(",");
            String[] room_beds = hotelRooms[3].split("-");

            /*
            Store data into Collections
             */
            //list of beds in each room of a hotel
            List<String> beds = new ArrayList<>(Arrays.asList(room_beds));

            address.put("Street Number", hotelAddress[0]);
            address.put("Street Name", hotelAddress[1]);
            address.put("City", hotelAddress[2]);
            address.put("Province", hotelAddress[3]);
            address.put("Postal Code", hotelAddress[4]);
            address.put("Longitude", hotelAddress[5]);
            address.put("Latitude", hotelAddress[6]);

            rooms.put("Start Date", hotelRooms[0]);
            rooms.put("End Date", hotelRooms[1]);
            rooms.put("Capacity", hotelRooms[2]);
            rooms.put("Beds", beds);
            rooms.put("Price", hotelRooms[4]);

            hotel.put("Name", hotelName);
            hotel.put("Address", address);
            hotel.put("Rooms", rooms);

            data.add(hotel);
        }
        return data;
    }

    public void writeFile(Hotel hotel) throws IOException {
        FileWriter file = new FileWriter(database);
        BufferedWriter bw = new BufferedWriter(file);

        StringBuilder hotelData = new StringBuilder();

        String name = hotel.getName();
        hotelData.append(name).append(";");

        Address hotelAddress = hotel.getAddress();
        hotelData.append(hotelAddress.getStreetNumber()).append(",");
        hotelData.append(hotelAddress.getStreetName()).append(",");
        hotelData.append(hotelAddress.getCity()).append(",");
        hotelData.append(hotelAddress.getProvince()).append(",");
        hotelData.append(hotelAddress.getPostalCode()).append(",");
        hotelData.append(hotelAddress.getLongitude()).append(",");
        hotelData.append(hotelAddress.getLatitude()).append(",").append(";");

        List<Room> hotelRooms = hotel.getRooms();
        for (Room room : hotelRooms) {
            hotelData.append(room.getSchedule()[0]).append(",");
            hotelData.append(room.getSchedule()[1]).append(",");
            hotelData.append(room.getCapacity()).append(",");

            List<Bed> roomBeds = room.getBeds();
            for (int i = 0; i < roomBeds.size() - 1; i++) {
                hotelData.append(roomBeds.get(i).getSize()).append("-");
            }

            hotelData.append(roomBeds.get(roomBeds.size() - 1).getSize()).append(",");

            hotelData.append(room.getPrice()).append(",");
        }

        bw.write(hotelData.toString());
    }
}