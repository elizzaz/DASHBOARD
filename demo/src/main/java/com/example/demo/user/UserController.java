package com.example.demo.user;

import com.example.demo.referentiel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.user.User;
import com.example.demo.referentiel.Repository;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/")
public class UserController {

    @Autowired
    com.example.demo.referentiel.Repository Repository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<User> user = new ArrayList<User>();

            if (name == null)
                Repository.findAll().forEach(user::add);
            else
                Repository.findByNameContaining(name).forEach(user::add);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = Repository.save(new User( user.getName(), user.getFirst_name(), user.getEmail(), user.getCity(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = Repository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/userupdate/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = Repository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setFirst_name(user.getFirst_name());
            _user.setEmail(user.getEmail());
            _user.setCity(user.getCity());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(Repository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userdelete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            Repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email, @RequestBody User user){
        Optional<User> userData = Repository.findByEmail(email);
        System.out.println(userData);

        if (userData.isPresent()) {
            User _user = userData.get();
             _user.getEmail();
             _user.getPassword();
             System.out.println(_user.getPassword());
                if (Objects.equals(_user.getPassword(), user.getPassword())) {
                        _user.setLoggedIn(true);
                        return new ResponseEntity<>(userData.get(),HttpStatus.OK);
                } else System.out.println("le 2");
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } else System.out.println("le 3");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

  //  @GetMapping("/logout/{email}")
   // public ResponseEntity<HttpStatus> logout(@PathVariable("email") String email) {
     //   Optional<User> userData =  Repository.logout(email);

       // if (User.isLoggedIn(true)) {
         //   User _user = userData.get();
          //  _user.setLoggedIn(false);
          //  return new ResponseEntity<>(HttpStatus.OK);
      //  } else  {
        //    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       // }
   // }

}

