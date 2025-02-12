
package com.ahmed.AhmedSpring.service;

import com.ahmed.AhmedSpring.entities.Student;
import java.util.List;


public interface StdService {  
    
    List<Student> findAll();
    Student findById(Long id);
    void saveStd(Student std);
    void deleteStd(Long id);
    
}
