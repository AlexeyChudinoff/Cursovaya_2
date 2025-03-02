package pro.sky.java.course2.examinerservice.service;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.domain.QuestionService;
import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new NoSuchElementException("Question not found");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(index);
    }

    @Override
    public Collection<Question> find(String question, String answer) {
        return questions.stream()
            .filter(q -> question == null || q.getQuestion().equalsIgnoreCase(question))
            .filter(q -> answer == null || q.getAnswer().equalsIgnoreCase(answer))
            .collect(Collectors.toList());
    }
}