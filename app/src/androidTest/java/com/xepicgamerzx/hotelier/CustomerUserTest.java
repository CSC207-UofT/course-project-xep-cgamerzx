package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.storage.user.CustomerUser;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CustomerUserTest {

    @Test
    public void testNewCustomerUser() {
        CustomerUser customerUser = new CustomerUser("Toronto",
                "25/12/2021-31/12/2021", "2");

        assertEquals(customerUser.getSelectedDestination(), "Toronto");
        assertEquals(customerUser.getSelectedDates(), "25/12/2021-31/12/2021");
        assertEquals(customerUser.getNumberOfGuests(), "2");
    }

    @Test
    public void testCustomerUserSetters() {
        CustomerUser customerUser = new CustomerUser("Toronto",
                "25/12/2021-31/12/2021", "2");

        customerUser.setSelectedDestination("Montreal");
        customerUser.setSelectedDates("25/12/2021-30/12/2021");
        customerUser.setNumberOfGuests("3");

        assertEquals(customerUser.getSelectedDestination(), "Montreal");
        assertEquals(customerUser.getSelectedDates(), "25/12/2021-30/12/2021");
        assertEquals(customerUser.getNumberOfGuests(), "3");
    }
}
