package NCDC;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BookDTO {

    private long bookID;
    @NotBlank(message = "Field can not be empty or consist of only white characters")
    @Pattern(regexp = "^A[a-z]*+\\s+[A-Z][a-z]*|^[A-Z][a-z]*+\\s+A[a-z]*", message = "Author forename or surname should start with \"A\" letter")
    private String author;
    @NotBlank(message = "Field can not be empty or consist of only white characters")
    private String title;
    @NotBlank(message = "Field can not be empty or consist of only white characters")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Please insert the number according to ISBN standard")
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
