package eLibrary;

import eLibrary.controller.LessonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-lesson-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-lesson-after.sql","/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LessonControllerTest {

    @Autowired
    private LessonController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/lesson/list"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='navbarSupportedContent']/div/a").string("fio"));
    }

    @Test
    public void lessonListTest() throws Exception {
        this.mockMvc.perform(get("/lesson/list"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='lesson-list']/a").nodeCount(2));
    }

    @Test
    public void addLessonToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart;
        multipart = multipart("/lesson/new")
                .param("name", "lesson3")
                .param("teacherId", "1")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated());

        this.mockMvc.perform(get("/lesson/list"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='lesson-list']/a").nodeCount(3));
    }

    @Test
    public void addSubGroupToLessonTest() throws Exception{
        MockHttpServletRequestBuilder multipart;
        multipart = multipart("/lesson/1")
                .param("name", "subGroup1")
                .param("lessonId", "1")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated());
    }
}
