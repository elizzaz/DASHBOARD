package com.example.demo.widget;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetService {

    public List<Widget> getWidget(){
        return List.of(
                new Widget(
                        1L,
                        "test",
                        "testwidget"
                )
        );
    }
}
