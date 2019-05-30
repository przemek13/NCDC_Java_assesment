package NCDC;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookRepository testBookRepository;

    private final Book testBook = new Book("Adam Mickiewicz", "Dziady", "978-3-16-148410-0");

    @Test
    public void returnWhenNoData() {
        //given
        //when
        List<Book> testOrganizations = testBookRepository.findAll();
        //then
        Assert.assertEquals(0, testOrganizations.size());
    }

    @Test
    public void saveAndReturn() {
        //given
        //when
        testBookRepository.save(testBook);
        var testComparedBook = testBookRepository.findByAuthor("Adam Mickiewicz");
        //then
        Assert.assertEquals(testBook, testComparedBook);
    }

    @Test
    public void persistAndReflectListSize() {
        //given
        //when
        entityManager.persist(testBook);
        List<Book> testOrganizations = testBookRepository.findAll();
        //then
        Assert.assertEquals(1, testOrganizations.size());
    }
}
