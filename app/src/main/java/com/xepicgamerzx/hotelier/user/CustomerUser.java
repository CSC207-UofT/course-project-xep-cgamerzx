package com.xepicgamerzx.hotelier.user;

import com.xepicgamerzx.hotelier.user.model.User;

public class CustomerUser {
    private String selectedDestination;
    private String selectedDates;
    private String numberOfGuests;

    public CustomerUser(String selectedDestination, String selectedDates, String numberOfGuests) {
        this.selectedDestination = selectedDestination;
        this.selectedDates = selectedDates;
        this.numberOfGuests = numberOfGuests;
    }


    public String getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(String selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public String getSelectedDates() {
        return selectedDates;
    }

    public void setSelectedDates(String selectedDates) {
        this.selectedDates = selectedDates;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
