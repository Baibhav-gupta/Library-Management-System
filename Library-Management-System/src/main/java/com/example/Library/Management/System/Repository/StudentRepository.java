package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //we are extending a hibernate class and extending its function


}
