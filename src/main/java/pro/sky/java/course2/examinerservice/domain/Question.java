package pro.sky.java.course2.examinerservice.domain;

import java.util.Objects;

public class Question {
  private final String question;
  private final String answer;

  public Question(String question, String answer) {
    this.question = Objects.requireNonNull(question, "Question cannot be null");
    this.answer = Objects.requireNonNull(answer, "Answer cannot be null");
    if (this.question.isBlank() || this.answer.isBlank()) {
      throw new IllegalArgumentException("Question and answer cannot be empty");
    }
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question that = (Question) o;
    return Objects.equals(question, that.question) &&
        Objects.equals(answer, that.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(question, answer);
  }
}