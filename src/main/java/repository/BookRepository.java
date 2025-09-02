package repository;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    List<Book> books = new ArrayList();


    public BookRepository() {
        // read file with list of books, and put them in book list.
        addBook(new Book("The Hobbit"));
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooks(){
        return List.copyOf(books);// copy of books to prevent changes!
    }

}
