package com.example.lab5student.Controller;

import com.example.lab5student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/student")
public class StudentController {

    List<Student> students = new ArrayList<>();

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        students.add(student);
        return "Student added";
    }

    @PutMapping("/edit/{index}")
    public String updateStudent(@RequestBody Student student,@PathVariable int index){
        if(index<0 || index>students.size()-1) {
            return "Not found 404";
        }
        else{
            students.set(index,student);
            return "Done";
        }

    }

    @DeleteMapping("/delete/{index}")
    public String deleteStudent(@PathVariable int index){
        if(index<0 || index>students.size()-1){
            return "Not found 404";
        }else{
            students.remove(index);
            return "Deleted";
        }

    }


    @GetMapping("/name/{index}")
    public String studentName(@PathVariable int index){
        if(index<0 || index>students.size()-1)return "Not found 404";
        else return students.get(index).getName();
    }

    @GetMapping("/age/{index}")
    public String studentAge(@PathVariable int index){
        if(index<0 || index>students.size()-1) return "Not found 404";
        else return String.valueOf(students.get(index).getAge());
    }

    @GetMapping("/college/degree/{index}")
    public String studentDegree(@PathVariable int index){
        if(index<0 || index>students.size()-1) return "Not found 404";
        else return students.get(index).getDegree();
    }

    @GetMapping("/study/status/{index}")
    public String studentStatus(@PathVariable int index){
        if(index<0 || index>students.size()-1) return "Not found 404";
        else return students.get(index).getStatus();
    }

    @GetMapping("/students")
    public List<Student> students(){
        return students;
    }
}
