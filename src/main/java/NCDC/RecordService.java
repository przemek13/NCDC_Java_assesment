package NCDC;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {

    private final BookRepository bookRepository;

    private RecordService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::bookToDTO).collect(Collectors.toList());
    }

    public void addBook(BookDTO bookDTO) {
        bookRepository.save(dtoToBook(bookDTO));
    }

    public void deleteBook(BookDTO bookDTO) {
        bookRepository.deleteById(bookDTO.getBookID());
    }

    Book dtoToBook(BookDTO bookDTO) {
        var book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }

    BookDTO bookToDTO(Book book) {
        var bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }
}