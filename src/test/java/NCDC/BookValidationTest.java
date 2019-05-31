package NCDC;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookValidationTest {

    private Validator validator;
    private final BookDTO testBook = new BookDTO();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void noViolationWhenForenameStartsWithA() {
        //given
        testBook.setAuthor("Adam Mickiewicz");
        testBook.setTitle("Dziady");
        testBook.setIsbn("978-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void noViolationWhenSurnameStartsWithA() {
        //given
        testBook.setAuthor("Jerzy Andrzejewski");
        testBook.setTitle("Popiół i Diament");
        testBook.setIsbn("978-3-16-148410-1");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void violationWhenForNameAndSureNameNotStartWithA() {
        //given
        testBook.setAuthor("Juliusz Słowacki");
        testBook.setTitle("Balladyna");
        testBook.setIsbn("978-3-16-148410-2");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenAuthorHasOnlyName() {
        //given
        testBook.setAuthor("Adam");
        testBook.setTitle("Dziady");
        testBook.setIsbn("978-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenTitleEmpty() {
        //given
        testBook.setAuthor("Adam Miokiewicz");
        testBook.setTitle("");
        testBook.setIsbn("978-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenTitleBlank() {
        //given
        testBook.setAuthor("Adam Miokiewicz");
        testBook.setTitle(" ");
        testBook.setIsbn("978-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenISBNTooLong() {
        //given
        testBook.setAuthor("Adam Miokiewicz");
        testBook.setTitle("Dziady");
        testBook.setIsbn("978-3-16-148410-01");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenISBNTooShort() {
        //given
        testBook.setAuthor("Adam Miokiewicz");
        testBook.setTitle("Dziady");
        testBook.setIsbn("78-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    public void violationWhenISBNContainsWrongTypeOfCharacter() {
        //given
        testBook.setAuthor("Adam Miokiewicz");
        testBook.setTitle("Dziady");
        testBook.setIsbn("A78-3-16-148410-0");
        //when
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(testBook);
        //then
        assertFalse(violations.isEmpty());
    }
}
