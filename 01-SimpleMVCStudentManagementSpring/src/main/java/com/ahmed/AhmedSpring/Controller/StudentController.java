
package com.ahmed.AhmedSpring.Controller;


import com.ahmed.AhmedSpring.entities.Student;
import com.ahmed.AhmedSpring.service.StdServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    
      
      private StdServiceImpl repo;
      @Autowired
      public StudentController(StdServiceImpl repo){
          this.repo=repo;
      }



    @GetMapping("/students")
    public String getStudents(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<Student> studentPage = repo.findStudentsWithPaginationAndSorting(page, size, sortField, sortDirection);

        model.addAttribute("listStds", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

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
