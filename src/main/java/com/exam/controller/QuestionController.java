package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizeService quizeService;

    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }



    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
        /*Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz =this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);*/

        Quiz quiz = this.quizeService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();

        List<Question> list = new ArrayList(questions);

        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));

        }

        list.forEach((q)->{
            q.setAnswer("");
        });

        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz =this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);


        //return ResponseEntity.ok(list);
    }




    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        return  this.questionService.getQuestion(quesId);
    }


    @DeleteMapping("/{quesId}")
    public  void delete(@PathVariable("quesId") Long quesId){
          this.questionService.deleteQuestion(quesId);
    }


    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {


       System.out.println(questions);
       double marksGot = 0;
        int correctAnswer = 0;
        int  attempted = 0;

     for(Question q:questions){
         Question question=  this.questionService.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                    correctAnswer++;

             double    markSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot += markSingle;
            }
            if(q.getGivenAnswer()!=null){
                attempted++;
            }

       }

     Map<String,Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
        return ResponseEntity.ok(map);
    }


}
