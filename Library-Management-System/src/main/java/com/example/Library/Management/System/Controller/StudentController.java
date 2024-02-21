package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServices ssObj;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student obj)
    {
        return ssObj.addStudent(obj);
    }
}
