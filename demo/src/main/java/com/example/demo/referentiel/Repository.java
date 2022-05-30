package com.example.demo.referentiel;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.User;
import com.example.demo.widget.Widget;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;

public interface Repository extends JpaRepository <User, Long> {

        List<User> findByNameContaining(String name);
        Optional<User> findByEmail(String email);
       // Optional<User> logout(String email);
}
