package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.dao.QuestionRepo;
import com.Ahmed.AhmedSpring.dao.QuizRepo;
import com.Ahmed.AhmedSpring.entities.Question;
import com.Ahmed.AhmedSpring.entities.QuestionDto;
import com.Ahmed.AhmedSpring.entities.Quiz;
import com.Ahmed.AhmedSpring.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
   private QuizRepo qr;
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions=questionRepo.findRandomQuestionsByCategory(category , numQ);
         Quiz quiz=Quiz.builder().title(title).questions(questions).build();
         qr.save(quiz);
         return new ResponseEntity<>("Quiz Created Successfully" , HttpStatus.CREATED);


    }


    public ResponseEntity<List<QuestionDto>> getQuiz(Integer id) {

      Quiz quiz=qr.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
      List<Question> questions=quiz.getQuestions();
        List<QuestionDto> myQues=new ArrayList<>();;
      for(Question q:questions){
          QuestionDto qd= new QuestionDto(q.getId()
                  , q.getQuestionTitle()
                  , q.getOption1()
                  , q.getOption2()
                  , q.getOption3()
          , q.getOption4());
          myQues.add(qd);
         }

        return new ResponseEntity<>(myQues , HttpStatus.OK);
    }

    public ResponseEntity<Integer> calcResult(Integer id, List<Response> responses) {
        Quiz q=qr.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        List<Question> questions=q.getQuestions();
        int res=0;

       for(int i=0; i< responses.size();i++){
           if(responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())){
               res++;
           }

       }
       return new ResponseEntity<>( res , HttpStatus.OK );
    }
}
