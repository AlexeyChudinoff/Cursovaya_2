package pro.sky.java.course2.examinerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.ExaminerService;
import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
  private final ExaminerService examinerService;

  public ExamController(ExaminerService examinerService) {
    this.examinerService = examinerService;
  }

  @GetMapping("/get/{amount}")
  public ResponseEntity<?> getQuestions(@PathVariable int amount) {
    try {
      return ResponseEntity.ok(examinerService.getQuestions(amount));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}