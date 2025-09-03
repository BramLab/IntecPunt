package repository;

import model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    List<Book> books = new ArrayList();


    public BookRepository() {
        // read file with list of books, and put them in book list.
        // If a book is added as a copy of a book, but with "new Book", then it is considered as another book.
       // Book book = new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512");
        //addBook(book);
        //addBook(book);
        //addBook(new Book("Heer Belisarius", "Robert Graves", 2010, "9789067282451"));
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooks(){
        return List.copyOf(books);// copy of books to prevent changes!
    }

    public Optional<Book> searchBook(String title, String author, int yearOfPublication){
        return books.stream()
                .filter(b -> b.getTitle().equals(title) & b.getAuthor().equals(author) & (b.getPublicationYear()==yearOfPublication))
                .findAny();
    }

    public Optional<Book> searchBook(String isbn){
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn) )
                .findAny();
    }

    public int countCopies(Book book){
        return Collections.frequency(books, book);
    }

}
