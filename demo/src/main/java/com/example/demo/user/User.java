package com.example.demo.user;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.ObjectInputStream;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;


    private boolean loggedIn;


    @Column(name = "first_name")
    private String first_name;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;



    public User(Long id, String name, String first_name, String email, String city, String password) {
        this.id = id;
        this.name = name;
        this.first_name = first_name;
        this.email = email;
        this.city = city;
        this.password = password;
        this.loggedIn = false;
    }

    public User(String name, String first_name, String email, String city, String password) {
        this.name = name;
        this.first_name = first_name;
        this.email = email;
        this.city = city;
        this.password = password;
        //this.service_id = service_id;
    }

    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

        @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                //", service_id='" + service_id + '\'' +
                '}';
    }
}
