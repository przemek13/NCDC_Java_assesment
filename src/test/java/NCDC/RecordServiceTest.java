package NCDC;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordServiceTest {

    @Mock
    private BookRepository testBookRepository;

    private RecordService testRecordService;

    private final BookDTO testBook = new BookDTO();

    @Before
    public void setUpTestBookService() {
        this.testRecordService = new RecordService(testBookRepository);
    }

    @Test
    public void saveMethodOnRepositoryWhenAddBook() {
        //given
        //when
        testRecordService.addBook(testBook);
        //then
        verify(testBookRepository, times(1)).save(ArgumentMatchers.any(Book.class));
    }

    @Test
    public void deleteByIdMethodOnRepositoryWhenDeleteBook() {
        //given
        //when
        testRecordService.deleteBook(testBook);
        //then
        verify(testBookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void findAllMethodOnRepositoryWhenGetAllBooks() {
        //given
        //when
        testRecordService.getAllBooks();
        //then
        verify(testBookRepository, times(1)).findAll();
    }
}
