package NCDC;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RunApp.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RecordControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private BookRepository testBookRepository;

    @After
    public void resetBookDb() {
        testBookRepository.deleteAll();
    }

    @Test
    public void getBooks() throws Exception {
        //given
        testBookRepository.save(new Book("Adam Mickiewicz", "Dziady", "978-3-16-148410-0"));
        //when
        //then
        mvc.perform(get("/records/books")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
        var testBook = testBookRepository.findByAuthor("Adam Mickiewicz");
        Assert.assertNotNull(testBook);
    }
}
