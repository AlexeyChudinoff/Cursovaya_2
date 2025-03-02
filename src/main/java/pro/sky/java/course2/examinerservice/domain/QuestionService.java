package pro.sky.java.course2.examinerservice.domain;

import java.util.Collection;

public interface QuestionService {
  Question add(String question, String answer);
  Question add(Question question);
  Question remove(Question question);
  Collection<Question> getAll();
  Question getRandomQuestion();
  Collection<Question> find (String question,String answer);
}