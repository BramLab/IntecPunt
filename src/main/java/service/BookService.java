package service;

import model.Book;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookRepository bookRepository;

    public BookService() {}

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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


    public int countCopies(String isbn){
        return bookRepository.countCopies(isbn); //repo seems best placed to count, no need to get list of book objects of same book.
    }

    public int countAvailableCopies(String isbn){
        //Complex: unavailable comes from loans
        int unavailableBooks = 1;
        return countCopies(isbn)-unavailableBooks;
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

}
