package pro.sky.java.course2.examinerservice.service;

import java.util.Collection;
import pro.sky.java.course2.examinerservice.domain.Question;

public interface ExaminerService {

  Collection<Question> getQuestions(int amount);


}
