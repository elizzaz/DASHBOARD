package com.example.demo.service;

import com.example.demo.referentiel.RepositoryService;
import com.example.demo.user.User;
import com.example.demo.service.Service;
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
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "api/")
public class ServiceController {

    @Autowired
    com.example.demo.referentiel.RepositoryService RepositoryService;

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices(@RequestParam(required = false) String title) {
        try {
            List<Service> service = new ArrayList<Service>();

            if (title == null)
                RepositoryService.findAll().forEach(service::add);
            else
                RepositoryService.findByTitleContaining(title).forEach(service::add);

            if (service.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(service, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/service")
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        try {
            Service _service = RepositoryService
                    .save(new Service(service.getId(), service.getTitle(), service.getDescription()));
            return new ResponseEntity<>(_service, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable("id") long id) {
        Optional<Service> serviceData = RepositoryService.findById(id);

        if (serviceData.isPresent()) {
            return new ResponseEntity<>(serviceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/serviceupdate/{id}")
    public ResponseEntity<Service> updateService(@PathVariable("id") long id, @RequestBody Service service) {
        Optional<Service> serviceData = RepositoryService.findById(id);

        if (serviceData.isPresent()) {
            Service _service = serviceData.get();
            service.setTitle(service.getTitle());
            service.setDescription(service.getDescription());
            return new ResponseEntity<>(RepositoryService.save(service), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/servicedelete/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable("id") long id) {
        try {
            RepositoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
