package com.Ahmed.AhmedSpring.controllers;

import com.Ahmed.AhmedSpring.entities.Question;
import com.Ahmed.AhmedSpring.entities.QuestionDto;
import com.Ahmed.AhmedSpring.entities.Response;
import com.Ahmed.AhmedSpring.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService qs;

    @PostMapping("/create") // {{url}}/quiz/create?category=..&numQ=..&title=..
    public ResponseEntity<String> createQuiz(@RequestParam String category
     , @RequestParam int numQ , @RequestParam String title
    ){

            qs.createQuiz(category , numQ , title);
        return new ResponseEntity<>("i am here" , HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuiz(@PathVariable Integer id){
       return qs.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitAnswers(@PathVariable Integer id
            , @RequestBody List<Response> responses){
           return qs.calcResult(id , responses);
    }

}
