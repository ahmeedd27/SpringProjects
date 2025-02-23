package com.Ahmed.AhmedSpring.controllers;

import com.Ahmed.AhmedSpring.entities.Question;
import com.Ahmed.AhmedSpring.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService qs;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(qs.getAllQuestion() , HttpStatus.OK);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String cat){
        return ResponseEntity.status(HttpStatus.OK).body(qs.fetchByCategory(cat));
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){

         return new ResponseEntity<>(qs.addQuestion(question) , HttpStatus.CREATED);
     }

     @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        qs.deleteQuestion(id);
        return "deleted successfully";
     }

}
