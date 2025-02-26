package pro.sky.java.course2.examinerservice;

import java.util.Set;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import java.util.Collection;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
  private final JavaQuestionService service = new JavaQuestionService();

  @Test
  void addByParamsShouldCreateAndStoreQuestion() {
    // Arrange
    String question = "Q1";
    String answer = "A1";

    // Act
    Question result = service.add(question, answer);

    // Assert
    assertEquals(question, result.getQuestion());
    assertEquals(answer, result.getAnswer());
    assertTrue(service.getAll().contains(result));
  }

  @Test
  void removeShouldDeleteExistingQuestion() {
    // Arrange
    Question q = service.add("Q2", "A2");

    // Act
    Question removed = service.remove(q);

    // Assert
    assertEquals(q, removed);
    assertFalse(service.getAll().contains(q));
  }

  @Test
  void getRandomQuestionShouldReturnFromStorage() {
    // Arrange
    Question q1 = service.add("Q3", "A3");
    Question q2 = service.add("Q4", "A4");

    // Act
    Question random = service.getRandomQuestion();

    // Assert
    assertTrue(Set.of(q1, q2).contains(random));
  }

  @Test
  void getAllShouldReturnUnmodifiableCollection() {
    // Arrange
    service.add("Q5", "A5");

    // Act
    Collection<Question> result = service.getAll();

    // Assert
    assertThrows(UnsupportedOperationException.class,
        () -> result.add(new Question("Q6", "A6")));
  }
}