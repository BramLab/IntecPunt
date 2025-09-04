package service;

import model.Book;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository bookRepository;
    private LoanService loanService;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public void addBook(Book book){
        bookRepository.addBook(book);
    }

    // first search/get the book
    public void softDeleteBook(Book book){
        book.setSoftDeleted(true);
    }

    public void softDeleteBook(Long id){
        Optional<Book> optionalBook = bookRepository.searchBook(id);
        if (optionalBook.isPresent()) softDeleteBook(optionalBook.get());
        optionalBook.ifPresent(this::softDeleteBook);
    }

    public Book searchBook(String title, String author, int publicationYear){
        Optional<Book> optionalBook = bookRepository.searchBook(title, author, publicationYear);
        return optionalBook.orElse(null);
    }

    public Book searchBook(String isbn){
        Optional<Book> optionalBook = bookRepository.searchBook(isbn);
        return optionalBook.orElse(null);
    }

    public Book searchBook(Long id){
        Optional<Book> optionalBook = bookRepository.searchBook(id);
        return optionalBook.orElse(null);
    }

    public int countAllCopies(String title, String author, int publicationYear){
        return bookRepository.countAllCopies(title, author, publicationYear);
    }

    public int countAllCopies(String isbn){
        return bookRepository.countAllCopies(isbn); // repo seems best placed to count, no need to get list of book objects of same book.
    }

    public int countAvailableCopies(String isbn){
        return countAllCopies(isbn) - loanService.countNonreturnedCopies(isbn);
    }

}
