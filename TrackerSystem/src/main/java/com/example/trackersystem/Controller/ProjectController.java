package com.example.trackersystem.Controller;

import com.example.trackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ts")
public class ProjectController {

    List<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public String add(@RequestBody Project project){
        projects.add(project);
        return "Added";
    }

    @GetMapping("/projects")
    public List<Project> displayProjects(){
        return projects;
    }

    @PutMapping("/update/{index}")
    public String updateProject(@PathVariable int index, @RequestBody Project project){
        if(index<0 || index>projects.size()-1) return "Not found 404";
        else{
             projects.set(index,project);
            return "Updated";}
    }

    @DeleteMapping("/delete/{index}")
    public String deleteProject(@PathVariable int index){
        if(index<0 || index>projects.size()-1) return "Not found 404";
        else return "Deleted";
    }

    @GetMapping("/change/{index}")
    public String changeStatus(@PathVariable int index){
        if(index<0 || index>projects.size()-1) {
            return "Not found 404";
        }else{
            if(projects.get(index).equals("done")) {
                projects.get(index).setStatus("not done");
            }
            else {
                projects.get(index).setStatus("done");
            }
            return "Changed";
        }

    }

    @GetMapping("/search/{title}")
    public Project search(@PathVariable String title){
        for(Project p : projects){
            if(p.getTitle().equals(title))return p;
        }
        return null;
    }

    @GetMapping("/displayall/{companyname}")
    public List<Project> displayAll(@PathVariable String companyname){
        List<Project> projectsBycompany = new ArrayList<>();
        for(Project p : projects){
            if(p.getCompanyName().equals(companyname)) projectsBycompany.add(p);
        }
        return projectsBycompany;
    }

}
