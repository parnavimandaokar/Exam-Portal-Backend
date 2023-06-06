package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizeService quizeService;

    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizeService.addQuiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizeService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<?> quizzes() {
        return ResponseEntity.ok(this.quizeService.getQuizzes());
    }



    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid) {
        return this.quizeService.getQuiz(qid);
    }




    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid") Long qid) {
        this.quizeService.deleteQuiz(qid);

    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
      Category category = new Category();
      category.setCid(cid);
       return  this.quizeService.getQuizzesOfCategory(category);

    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes() {
        return  this.quizeService.getActiveQuizzes();

    }

    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return  this.quizeService.getActiveQuizzesOfCategory(category);
    }
}
