package com.example.demo.service;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    public List<Service> getWidget(){
        return List.of(
                new Service(
                        1L,
                        "test",
                        "testpourservice"
                )
        );
    }
}
