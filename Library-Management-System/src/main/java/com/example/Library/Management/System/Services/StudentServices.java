package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {
    @Autowired
    private StudentRepository slObj;
    public String addStudent(Student obj)
    {
        slObj.save(obj);
        return "Student hasbeen saved to the Db";
    }
}
