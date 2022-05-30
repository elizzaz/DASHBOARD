package com.example.demo.widget;

import com.example.demo.referentiel.RepositoryWidget;
import com.example.demo.user.User;
import com.example.demo.widget.Widget;
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

import com.example.demo.widget.Widget;
import com.example.demo.referentiel.RepositoryWidget;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "api/")
public class WidgetController {

    @Autowired
    com.example.demo.referentiel.RepositoryWidget RepositoryWidget;

    @GetMapping("/widgets")
    public ResponseEntity<List<Widget>> getAllWidgets(@RequestParam(required = false) String title) {
        try {
            List<Widget> widget = new ArrayList<Widget>();

            if (title == null)
                RepositoryWidget.findAll().forEach(widget::add);
            else
                RepositoryWidget.findByTitleContaining(title).forEach(widget::add);

            if (widget.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(widget, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/widget")
    public ResponseEntity<Widget> createWidget(@RequestBody Widget widget) {
        try {
            Widget _widget = RepositoryWidget
                    .save(new Widget(widget.getId(), widget.getTitle(), widget.getDescription()));
            return new ResponseEntity<>(_widget, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/widget/{id}")
    public ResponseEntity<Widget> getWidgetById(@PathVariable("id") long id) {
        Optional<Widget> widgetData = RepositoryWidget.findById(id);

        if (widgetData.isPresent()) {
            return new ResponseEntity<>(widgetData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/widgetupdate/{id}")
    public ResponseEntity<Widget> updateWidget(@PathVariable("id") long id, @RequestBody Widget widget) {
        Optional<Widget> widgetData = RepositoryWidget.findById(id);

        if (widgetData.isPresent()) {
            Widget _widget = widgetData.get();
            _widget.setTitle(widget.getTitle());
            _widget.setDescription(widget.getDescription());
            return new ResponseEntity<>(RepositoryWidget.save(_widget), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/widgetdelete/{id}")
    public ResponseEntity<HttpStatus> deleteWidget(@PathVariable("id") long id) {
        try {
            RepositoryWidget.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
