package pro.sky.java.course2.examinerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

class JavaQuestionServiceTest {

  private final JavaQuestionService service = new JavaQuestionService();

  @Test
  void find_WithQuestionAndAnswer_ReturnsMatches() {

    Question q1 = new Question("Q1", "A1");
    Question q2 = new Question("Q2", "A2");
    service.add(q1);
    service.add(q2);
    Collection<Question> result = service.find("Q1", "A1");
    assertEquals(1, result.size());
    assertTrue(result.contains(q1));
  }

  @Test
  void find_WithoutParameters_ReturnsAll() {
    service.add(new Question("Q1", "A1"));
    service.add(new Question("Q2", "A2"));
    Collection<Question> result = service.find(null, null);
    assertEquals(2, result.size());
  }

  @Test
  void addByParamsShouldCreateAndStoreQuestion() {
    String question = "Q1";
    String answer = "A1";
    Question result = service.add(question, answer);
    assertEquals(question, result.getQuestion());
    assertEquals(answer, result.getAnswer());
    assertTrue(service.getAll().contains(result));
  }

  @Test
  void removeShouldDeleteExistingQuestion() {
    Question q = service.add("Q2", "A2");
    Question removed = service.remove(q);
    assertEquals(q, removed);
    assertFalse(service.getAll().contains(q));
  }

  @Test
  void removeNonExistingQuestionShouldThrow() {
    Question q = new Question("Q3", "A3");
    assertThrows(NoSuchElementException.class, () -> service.remove(q));
  }

  @Test
  void getRandomQuestionShouldReturnFromStorage() {
    Question q1 = service.add("Q3", "A3");
    Question q2 = service.add("Q4", "A4");
    Question random = service.getRandomQuestion();
    assertTrue(Set.of(q1, q2).contains(random));
  }

  @Test
  void getAllShouldReturnUnmodifiableCollection() {
    service.add("Q5", "A5");
    Collection<Question> result = service.getAll();
    assertThrows(UnsupportedOperationException.class,
        () -> result.add(new Question("Q6", "A6")));
  }
}