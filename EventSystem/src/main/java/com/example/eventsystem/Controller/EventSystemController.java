package com.example.eventsystem.Controller;

import com.example.eventsystem.Model.EventSystem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eventsys")
public class EventSystemController {

    List<EventSystem> events= new ArrayList<>();


    @GetMapping("/displayall")
    public List<EventSystem> display(){
        return events;
    }

    @GetMapping("/event/{index}")
    public EventSystem event(@PathVariable int index){
        return events.get(index);
    }

    @DeleteMapping("/delete/{index}")
    public String deleteEvent(@PathVariable int index , @RequestBody EventSystem event){
        if(index<0 || index> events.size()-1) return "Not Found 404";
        else{
            events.remove(index);
            return "Deleted";
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody EventSystem event){
        events.add(event);
        return "Added";
    }
    @PutMapping("/update/{index}")
    public String updateEvent(@PathVariable int index , @RequestBody EventSystem event){
        if(index<0 || index> events.size()-1) return "Not Found 404";
        else{
        events.set(index,event);
        return "Updated";
        }
    }

    @PutMapping("/change/{index}/{capacity}")
    public String changeCapacity(@PathVariable int index , @PathVariable int capacity){
        if(index<0 || index> events.size()-1) return "Not Found 404";
        else {
            EventSystem ev = events.get(index);
            ev.setCapacity(capacity);
            return "Capacity changed";
        }
    }

    @GetMapping("/searchid/{id}")
    public EventSystem searchById(@PathVariable String id){
        for(EventSystem ev : events){
            if(ev.getId().equals(id)) return ev;
        }
        return null;
    }

}
