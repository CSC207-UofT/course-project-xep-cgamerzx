package com.xepicgamerzx.hotelier.storage.user;

public class CustomerUser {
    private String selectedDestination;
    private String selectedDates;
    private String numberOfGuests;

    /**
     * Create a new CustomerUser.
     *
     * @param selectedDestination the destination the user wants to search for
     * @param selectedDates       the dates the user wants to search for
     * @param numberOfGuests      the number of guests the user is travelling with
     */
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
