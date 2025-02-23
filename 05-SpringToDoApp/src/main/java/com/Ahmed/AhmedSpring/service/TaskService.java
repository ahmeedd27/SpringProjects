package com.Ahmed.AhmedSpring.service;

import com.Ahmed.AhmedSpring.enity.Task;
import com.Ahmed.AhmedSpring.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

   private final TaskRepo tr;
   @Autowired
   public TaskService(TaskRepo tr){
       this.tr=tr;
   }

    public List<Task> getAllTasks() {
    return tr.findAll();
   }

    public void saveTask(String title) {
       Task t=Task.builder().title(title).completed(false).build();
       tr.save(t);
    }

    public void deleteTask(Long id) {
      tr.deleteById(id);
   }

   public void updateTask(Long id , String newTitle){
       Task t=tr.findById(id).orElseThrow(() -> new RuntimeException());
       t.setTitle(newTitle);
       tr.save(t);

   }

    public void toggleTask(Long id) {
       Task t=tr.findById(id).orElseThrow(NoSuchElementException::new);
       t.setCompleted(true);
       tr.save(t);
    }
}
