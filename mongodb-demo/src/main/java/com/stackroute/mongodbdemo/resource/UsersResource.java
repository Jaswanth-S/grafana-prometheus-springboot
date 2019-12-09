package com.stackroute.mongodbdemo.resource;

import com.stackroute.mongodbdemo.document.Users;
import com.stackroute.mongodbdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/v1")
public class UsersResource {

    private UserRepository userRepository;

    @Autowired
    public UsersResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<Users> getAll() {

        return userRepository.findAll();
    }

}
