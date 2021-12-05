package com.xepicgamerzx.hotelier;

import com.xepicgamerzx.hotelier.storage.user.model.User;
import static org.junit.Assert.assertEquals;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class UserTest {

    @Test
    public void testNewUser() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        assertEquals(user.getUserName(), "epic_gamer");
        assertEquals(user.getPassword(), "password123");
        assertEquals(user.getEmail(), "epicgamer@gmail.com");
    }

    @Test
    public void testUserSetters() {
        User user = new User("epic_gamer", "password1",
                "epicgamer@gmail.com");
        user.setId(1);
        user.setUserName("epic_gamer2");
        user.setPassword("password2");
        user.setEmail("epicgamer2@gmail.com");

        assertEquals(user.getId(), 1);
        assertEquals(user.getUserName(), "epic_gamer2");
        assertEquals(user.getPassword(), "password2");
        assertEquals(user.getEmail(), "epicgamer2@gmail.com");
    }

    @Test
    public void testUserWithNoIdReference() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        assertEquals(user.getId(), 0);
    }

}
