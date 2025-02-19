
package com.ahmed.AhmedSpring.Controller;


import com.ahmed.AhmedSpring.entities.Student;
import com.ahmed.AhmedSpring.service.StdServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    
      
      private StdServiceImpl repo;
      @Autowired
      public StudentController(StdServiceImpl repo){
          this.repo=repo;
      }
    
    @GetMapping("/Students")
    public String getStudent(Model m){
        List<Student> list=repo.findAll();
        m.addAttribute("listStds",list);
        return "student-page";
    }
    @GetMapping("/create")
    public String newOne(Model m){
        Student s=new Student();
        m.addAttribute("student", s);
        return "create-student";
    }
    
    @PostMapping("/newstudent")
    public String createStudent(@ModelAttribute("student") Student std ){
        
        repo.saveStd(std);
        
        return "redirect:/Students";
    }
    
    @GetMapping("/students/delete/{id}")
    public String deleteStd(@PathVariable("id") Long id){
        repo.deleteStd(id);
        return "redirect:/Students";
    }
    
 
    
    @GetMapping("/students/edit/{id}")
    public String newUpdate(@PathVariable("id") Long id
    , Model m
            ){
        Student std=repo.findById(id);
        m.addAttribute("student", std);
        return "edit-std";
    }
    
    @PostMapping("/students/{id}")
    public String updateStudent(@ModelAttribute("student") Student std ){
        
        repo.saveStd(std);
        
        return "redirect:/Students";
    }
    
}
