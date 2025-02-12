/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ahmed.AhmedSpring.service;

import com.ahmed.AhmedSpring.dao.StdRepo;
import com.ahmed.AhmedSpring.entities.Student;
import com.ahmed.AhmedSpring.service.StdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StdServiceImpl implements StdService {
    
    private StdRepo sr;
    @Autowired
    public StdServiceImpl(StdRepo sr){
        this.sr=sr;
    }

    @Override
    public List<Student> findAll() {
       return sr.findAll();
    }

    @Override
    public Student findById(Long id) {
      if(sr.findById(id).isPresent()){
          return sr.findById(id).get();
      }
      return null;
    }

    @Override
    public void saveStd(Student std) {
       sr.save(std);
    }

    @Override
    public void deleteStd(Long id) {
       Student std=sr.findById(id).get();
       sr.delete(std);
    }
    
}
