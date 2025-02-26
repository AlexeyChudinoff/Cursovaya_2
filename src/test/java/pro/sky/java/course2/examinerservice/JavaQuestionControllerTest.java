package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.java.course2.examinerservice.controller.JavaQuestionController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JavaQuestionController.class)
class JavaQuestionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Mock
  private JavaQuestionService service;

  @Test
  void addQuestionShouldCallService() throws Exception {
    Question expected = new Question("Q", "A");
    when(service.add("Q", "A")).thenReturn(expected);

    mockMvc.perform(post("/exam/java/add")
            .param("question", "Q")
            .param("answer", "A"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.question").value("Q"));
  }
}