package NCDC;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView("/records/books");
        modelAndView.addObject("books", recordService.getAllBooks());
        return modelAndView;
    }

    @GetMapping("/books/add")
    public ModelAndView addBookForm(BookDTO book) {
        ModelAndView modelAndView = new ModelAndView("/records/book_add");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/books/add")
    public ModelAndView addBook(@Valid @ModelAttribute("book") BookDTO book, BindingResult br) {
        ModelAndView modelAndViewError = new ModelAndView("/records/book_add");
        modelAndViewError.addObject("book", book);
        if (br.hasErrors()) {
            return modelAndViewError;
        }
        recordService.addBook(book);
        ModelAndView modelAndViewOK = new ModelAndView("redirect:/records/books");
        modelAndViewOK.addObject("book", book);
        return modelAndViewOK;
    }

    @GetMapping("/books/delete")
    public ModelAndView deleteBookForm(BookDTO book) {
        ModelAndView modelAndView = new ModelAndView("/records/book_delete");
        modelAndView.addObject("book", book);
        modelAndView.addObject("books", recordService.getAllBooks());
        return modelAndView;
    }

    @PostMapping("/books/delete")
    public ModelAndView deleteBook(@ModelAttribute("book") BookDTO book) {
        ModelAndView modelAndViewOK;
        if (book.getBookID() == 0) {
            modelAndViewOK = new ModelAndView("redirect:/records/books");
        } else {
            modelAndViewOK = new ModelAndView("/records/book_delete_success");
            modelAndViewOK.addObject("book", book.getBookID());
            recordService.deleteBook(book);
        }
        return modelAndViewOK;
    }
}