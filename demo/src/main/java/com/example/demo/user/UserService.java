package com.example.demo.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUsers(){
        return List.of(
                new User(
                        1L,
                        "Jamal",
                        "Mariam",
                        "mariam.jama@gmail.com",
                        "Marseille",
                        "mdp"
                )
        );
    }
}
