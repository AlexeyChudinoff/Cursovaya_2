package pro.sky.java.course2.examinerservice.controller;

import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;
import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
  private final JavaQuestionService questionService;

  public JavaQuestionController(JavaQuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/add")
  public Question addQuestion(@RequestParam String question,
      @RequestParam String answer) {
    return questionService.add(question, answer);
  }

  @GetMapping("/remove")
  public ResponseEntity<Question> removeQuestion(@RequestParam String question,
      @RequestParam String answer) {
    try {
      return ResponseEntity.ok(questionService.remove(new Question(question, answer)));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/find")
  public ResponseEntity<Collection<Question>> findQuestions(
      @RequestParam(required = false) String question,
      @RequestParam(required = false) String answer) {

    Collection<Question> result = questionService.find(question, answer);

    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public Collection<Question> getAllQuestions() {
    return questionService.getAll();
  }
}