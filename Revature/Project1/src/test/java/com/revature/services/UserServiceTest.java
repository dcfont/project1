package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserDAO userDAO = Mockito.mock(UserDAO.class);
    private UserService userService;

    public UserServiceTest(){
        this.userService = new UserService(userDAO);
    }

    @Test
    public void testInvalidUsername() {
        //arrange
        String expectedUsername = "nothere";
        String expectedPassword = "pass123";
        User expectedOutput = null;
        Mockito.when(userDAO.getUserUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualoutput = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput,actualoutput);

    }

    @Test
    public void testInalidPassword() {
        //arrange
        String expectedUsername = "user123";
        String expectedPassword = "nothere";
        User expectedOutput = null;

        Mockito.when(userDAO.getUserUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualoutput = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput,actualoutput);
    }

    @Test
    public void testValidCredentials() {
        //arrange
        String expectedUsername = "user123";
        String expectedPassword = "pass123";
        User expectedOutput = new User(1, expectedUsername, expectedPassword, "first", "last", "flast@user.com", "Employee");
        Mockito.when(userDAO.getUserUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        assertEquals(expectedOutput, actualOutput);


    }

    @Test
    public void testCreateEmployee() {
        //arrange
        User user = new User(1, "expectedUsername", "expectedPassword", "first", "last", "flast@user.com", "Employee");

        //act
        userService.createEmployee(user);

        //assert
        Mockito.verify(userDAO).createEmployee(user);
    }

    @Test
    public void testCreateManager() {
        //arrange
        User user = new User(1, "expectedUsername", "expectedPassword", "first", "last", "flast@user.com", 1);

        //act
        userService.createManager(user);

        //assert
        Mockito.verify(userDAO).createManager(user);
    }
}