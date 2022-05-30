package com.example.demo.referentiel;

import com.example.demo.widget.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryWidget extends JpaRepository <Widget, Long> {

        List<Widget>findByTitleContaining(String title);
}


