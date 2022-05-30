package com.example.demo.referentiel;

import com.example.demo.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryService extends JpaRepository <Service, Long> {

        List<Service>findByTitleContaining(String title);
}


