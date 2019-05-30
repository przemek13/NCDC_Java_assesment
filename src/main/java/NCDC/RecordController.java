package NCDC;

import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(RecordController.class);

    private final RecordService recordService;

    private RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/books")
    private ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView("/records/books");
        modelAndView.addObject("books", recordService.getAllBooks());
        LOG.trace("The record's list was successfully retrieved and added to the view");
        return modelAndView;
    }

    @GetMapping("/books/add")
    private ModelAndView addBookForm(BookDTO book) {
        ModelAndView modelAndView = new ModelAndView("/records/book_add");
        modelAndView.addObject("book", book);
        LOG.trace("The form to add new record was added to the view");
        return modelAndView;
    }

    @PostMapping("/books/add")
    private ModelAndView addBook(@Valid @ModelAttribute("book") BookDTO book, BindingResult br) {
        ModelAndView modelAndViewError = new ModelAndView("/records/book_add");
        modelAndViewError.addObject("book", book);
        if (br.hasErrors()) {
            LOG.warn("Wrong argument was submitted in the form");
            return modelAndViewError;
        }
        recordService.addBook(book);
        LOG.trace("The record was successfully added to the list");
        ModelAndView modelAndViewOK = new ModelAndView("redirect:/records/books");
        return modelAndViewOK;
    }

    @GetMapping("/books/delete")
    private ModelAndView deleteBookForm(BookDTO book) {
        ModelAndView modelAndView = new ModelAndView("/records/book_delete");
        modelAndView.addObject("book", book);
        modelAndView.addObject("books", recordService.getAllBooks());
        LOG.trace("The list of records to delete was added to the view");
        return modelAndView;
    }

    @PostMapping("/books/delete")
    private ModelAndView deleteBook(@ModelAttribute("book") BookDTO book) {
        ModelAndView modelAndViewOK;
        if (book.getBookID() == 0) {
            modelAndViewOK = new ModelAndView("redirect:/records/books");
            LOG.warn("Empty list - no record could be deleted");
        } else {
            modelAndViewOK = new ModelAndView("/records/book_delete_success");
            modelAndViewOK.addObject("book", book.getBookID());
            recordService.deleteBook(book);
            LOG.trace("The record was successfully deleted");
        }
        return modelAndViewOK;
    }
}