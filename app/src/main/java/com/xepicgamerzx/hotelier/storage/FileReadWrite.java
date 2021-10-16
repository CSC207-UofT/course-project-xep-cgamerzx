package com.xepicgamerzx.hotelier.storage;

import java.util.Arrays;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;


public class FileReadWrite {

    private File database = new File("HotelData.csv"); //database file name

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

        FileReader file = new FileReader(this.database);
        BufferedReader br = new BufferedReader(file);

        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            Hashtable<String, Object> hotel = new Hashtable<>(); //mapping for data for each hotel
            Hashtable<String, String> address = new Hashtable<>(); //mapping for address data of each hotel
            Hashtable<String, Object> rooms = new Hashtable<>(); //mapping for room data of each hotel

            /*
            Read and parse data
             */
            String[] hotel_data = line.split(";");
            String hotel_name = hotel_data[0];
            String[] hotel_address = hotel_data[1].split(",");
            String[] hotel_rooms = hotel_data[2].split(",");
            String[] room_beds = hotel_rooms[3].split("-");

            /*
            Store data into Collections
             */
            //list of beds in each room of a hotel
            List<String> beds = new ArrayList<>(Arrays.asList(room_beds));

            address.put("Street Number", hotel_address[0]);
            address.put("Street Name", hotel_address[1]);
            address.put("City", hotel_address[2]);
            address.put("Province", hotel_address[3]);
            address.put("Postal Code", hotel_address[4]);
            address.put("Longitude", hotel_address[5]);
            address.put("Latitude", hotel_address[6]);

            rooms.put("Start Date", hotel_rooms[0]);
            rooms.put("End Date", hotel_rooms[1]);
            rooms.put("Capacity", hotel_rooms[2]);
            rooms.put("Beds", beds);

            hotel.put("Name", hotel_name);
            hotel.put("Address", address);
            hotel.put("Rooms", rooms);

            data.add(hotel);
        }
        return data;
    }

    public void writeFile() throws IOException {
        FileWriter file = new FileWriter(this.database);
        BufferedWriter bw = new BufferedWriter(file);

    }
}
