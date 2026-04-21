package com.example.hw04_gymlog_v300;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginUnitTest {

    @Test
    public void loginInput_notEmpty() {
        String username = "admin1";
        String password = "admin1";

        assertFalse(username.trim().isEmpty());
        assertFalse(password.trim().isEmpty());
    }
}