package eLibrary.integrationTests;

import eLibrary.controller.BookController;
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
@Sql(value = {"/create-user-before.sql", "/create-book-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-book-after.sql","/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addBookTest() throws Exception {
        MockHttpServletRequestBuilder multipart;
        multipart = multipart("/book/new")
                .file("file", "asd".getBytes())
                .file("image", "img".getBytes())
                .param("author", "author")
                .param("name", "book")
                .param("description", "1das")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated());
    }

    @Test
    public void bookListTest() throws Exception {
        this.mockMvc.perform(get("/book/list"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='books-list']/div").nodeCount(2));
    }

//    @Test
//    public void bookEditTest() throws Exception {
//        MockHttpServletRequestBuilder multipart;
//        multipart = multipart("/book/1")
//                .file("file", "asd".getBytes())
//                .file("image", "img".getBytes())
//                .param("author", "author")
//                .param("name", "book1")
//                .param("description", "1das")
//                .with(csrf());
//
//        this.mockMvc.perform(multipart)
//                .andDo(print())
//                .andExpect(authenticated());
//
//        this.mockMvc.perform(get("/book/list"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id='books-list']/div").nodeCount(2))
//                .andExpect(xpath("//*[@id='name']").string("book1"));
//    }
}
