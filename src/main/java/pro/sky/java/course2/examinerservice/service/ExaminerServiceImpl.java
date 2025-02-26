package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import java.util.*;
import pro.sky.java.course2.examinerservice.domain.QuestionService;

@Service
public class ExaminerServiceImpl implements ExaminerService {
  private final QuestionService questionService;

  public ExaminerServiceImpl(QuestionService questionService) {
    this.questionService = questionService;
  }

  @Override
  public Collection<Question> getQuestions(int amount) {
    Collection<Question> allQuestions = questionService.getAll();

    if (amount > allQuestions.size()) {
      throw new IllegalArgumentException("Not enough questions");
    }

    Set<Question> uniqueQuestions = new HashSet<>();
    while (uniqueQuestions.size() < amount) {
      uniqueQuestions.add(questionService.getRandomQuestion());
    }

    return uniqueQuestions;
  }
}