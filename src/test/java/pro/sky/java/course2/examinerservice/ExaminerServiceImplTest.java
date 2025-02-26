package pro.sky.java.course2.examinerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.domain.QuestionService;
import pro.sky.java.course2.examinerservice.service.ExaminerServiceImpl;

// ExaminerServiceImplTest
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
  @Mock
  private QuestionService questionService;

  @InjectMocks
  private ExaminerServiceImpl examinerService;

  @Test
  void getQuestionsShouldReturnExactAmount() {
    // Мокирование
    when(questionService.getAll())
        .thenReturn(Set.of(new Question("Q1", "A1"), new Question("Q2", "A2")));
    when(questionService.getRandomQuestion())
        .thenReturn(new Question("Q1", "A1"))
        .thenReturn(new Question("Q2", "A2"));

    Collection<Question> result = examinerService.getQuestions(2);
    assertEquals(2, result.size());
  }
}