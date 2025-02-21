
package com.ahmed.AhmedSpring.service;

import com.ahmed.AhmedSpring.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StdService {  
    
    List<Student> findAll();
    Student findById(Long id);
    void saveStd(Student std);
    void deleteStd(Long id);

    Page<Student> findStudentsWithPaginationAndSorting(int page, int size, String sortField, String sortDirection);
    
}
