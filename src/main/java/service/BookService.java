package service;

import model.Book;
import repository.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public void insertBook(Book book){
        bookRepository.addBook(book);
    }

//    public Book searchBook(String title){
//        return bookRepository.
//    }
}
