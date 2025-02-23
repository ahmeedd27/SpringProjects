package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.dao.QuestionRepo;
import com.Ahmed.AhmedSpring.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo qr;

    public List<Question> getAllQuestion(){
        return qr.findAll();
    }
    public List<Question> fetchByCategory(String cat){
      return   qr.findQuestionByCategory(cat);
    }


    public Question addQuestion(Question question) {

        return qr.save(question);
    }

    public void deleteQuestion(Integer id) {
        qr.deleteById(id);
    }
}
