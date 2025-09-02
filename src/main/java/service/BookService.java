package service;

import model.Book;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public void addBook(Book book){
        bookRepository.addBook(book);
    }

//    public Book searchBook(String title){
//        return bookRepository.
//    }


    public int countCopies(Book book){
        return bookRepository.countCopies(book); //repo seems best placed to count, no need to get list of book objects of same book.
    }

    public Book searchBook(String title, String author, int yearOfPublication){
        Optional<Book> optionalBook = bookRepository.searchBook(title, author, yearOfPublication);
        // Book book = optionalBook.isPresent() ? optionalBook.get() : null;
        // Can be replaced with single expression in functional style
        return optionalBook.orElse(null);
    }

    public Book searchBook(String isbn){
        Optional<Book> optionalBook = bookRepository.searchBook(isbn);
        // Book book = optionalBook.isPresent() ? optionalBook.get() : null;
        // Can be replaced with single expression in functional style
        return optionalBook.orElse(null);
    }

    public int countAvailableCopies(Book book){
        //Complex: unavailable comes from loans
        int unavailableBooks = 1;
        return countCopies(book)-unavailableBooks;
    }

}
