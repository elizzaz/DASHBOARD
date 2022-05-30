package com.example.demo.service;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


        public Service() {

        }

        public Service(Long id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public Service(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {return description;}

        public void setDescription(String description) {
            this.description = description;
        }


        @Override
        public String toString() {
            return "user{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

