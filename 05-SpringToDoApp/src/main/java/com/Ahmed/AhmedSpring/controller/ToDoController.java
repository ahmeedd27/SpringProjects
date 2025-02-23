package com.Ahmed.AhmedSpring.controller;

import com.Ahmed.AhmedSpring.enity.Task;
import com.Ahmed.AhmedSpring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController {

    private final TaskService ts;
    @Autowired
    public ToDoController(TaskService ts){
        this.ts=ts;
    }

    @GetMapping("/tasks")
    public String getTasks(Model m){
        List<Task> tasks=ts.getAllTasks();
        m.addAttribute("tasks" , tasks);
        return "tasks"; // to display all tasks must have the list of tasks
    }
    @PostMapping("/createtask")
    public String createTasks(@RequestParam String title){
      ts.saveTask(title);
      return "redirect:/tasks";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
       ts.deleteTask(id);

        return "redirect:/tasks";
    }
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id , @ModelAttribute("tasks") Task t){

        ts.updateTask(id , t.getTitle());
        return "redirect:/tasks";

    }
    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable("id") Long id){
        ts.toggleTask(id);
         return "redirect:/tasks";
    }


}
