package com.xepicgamerzx.hotelier.storage.user.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User entity
 */
@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private String userName;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "userType")
    private String userType;

    private ArrayList<String> favHotelIds = new ArrayList<>();
    private ArrayList<String> recentSearches = new ArrayList<>();

    /**
     * Create a new User.
     *
     * @param userName this user's username
     * @param password this user's password
     * @param email    this user's email address
     */
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getFavHotelIds() {
        return favHotelIds;
    }

    public void setFavHotelIds(ArrayList<String> favHotelIds) {
        this.favHotelIds = favHotelIds;
    }

    /**
     * Get a list of the ids of this user's favourite hotels.
     *
     * @return a list of the user's favourite hotels' ids
     */
    public List<Long> getFavHotelIdsL() {
        return favHotelIds.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    /**
     * Adds a hotel to a user's favourites.
     *
     * @param hotelId the id of the hotel to add to favourites.
     */
    public void addFavHotel(String hotelId) {
        // Need to compare strings for some reason, look into this, but low priority for now.
        if (!favHotelIds.contains(hotelId)) {
            favHotelIds.add(hotelId);
        }
    }

    /**
     * Adds a hotel to a user's favourites.
     *
     * @param hotel the hotel to add to favourites.
     */
    public void addFavHotel(Hotel hotel) {
        addFavHotel(String.valueOf(hotel.hotelId));
    }

    /**
     * Removes a hotel from a user's favourites.
     *
     * @param hotelId the id of the hotel to remove from favourites.
     */
    public void removeFavHotel(String hotelId) {
        favHotelIds.remove(String.valueOf(hotelId));
    }

    /**
     * Removes a hotel from a user's favourites.
     *
     * @param hotel the hotel to remove from favourites.
     */
    public void removeFavHotel(Hotel hotel) {
        removeFavHotel(String.valueOf(hotel.hotelId));
    }

    public ArrayList<String> getRecentSearches() {
        return recentSearches;
    }

    public void setRecentSearches(ArrayList<String> recentSearches) {
        this.recentSearches = recentSearches;
    }

    /**
     * Adds a destination to a user's recent searches.
     *
     * @param destinationItem the destination to add to recent searches.
     */
    public void addRecentSearches(String destinationItem) {
        recentSearches.remove(destinationItem);
        recentSearches.add(destinationItem);
    }

    /**
     * Removes a destination from a user's recent searches.
     *
     * @param destinationItem the destination to remove from recent searches.
     */
    public void removeRecentSearches(String destinationItem) {
        recentSearches.remove(destinationItem);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null)
            return false;
        return getFavHotelIds() != null ? getFavHotelIds().equals(user.getFavHotelIds()) : user.getFavHotelIds() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFavHotelIds() != null ? getFavHotelIds().hashCode() : 0);
        return result;
    }
}
