package com.volkodav4ik;

import com.volkodav4ik.api.ApiService;
import com.volkodav4ik.model.User;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApiService apiService = ApiService.getInstance();
        // Create
        apiService.addUser(new User("Alex", 23));
        apiService.addUser(new User("Ben", 34));
        apiService.addUser(new User("Carl", 35));
        apiService.addUser(new User("Denis", 55));
        apiService.addUser(new User("Evan", 14));
        // Read
        System.out.println(apiService.getUserId(1).toString());
        System.out.println(apiService.getUserName("Carl"));
        System.out.println(apiService.getAllUser().toString());
        // Update
        User test = new User(1, "TestUpdate", 100);
        apiService.updateUser(test);
        // Delete
        apiService.deleteId(2);
        apiService.deleteName("Denis");

        System.out.println(apiService.getAllUser().toString());
    }
}
