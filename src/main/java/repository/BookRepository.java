package repository;

import model.Book;
import model.Member;

import java.util.*;

public class BookRepository {
    List<Book> books = new ArrayList();

    public BookRepository() {
        // Could/should be: read file with list of books, and put them in book list.
        addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        addBook(new Book("Heer Belisarius", "Robert Graves", 2010, "9789067282451"));
        addBook(new Book("OCA java 8", "Jeanne Boyarsky", 2015,"9789067282446"));
        addBook(new Book("Harry Poter","J.K. Rowling",2012,"9789067282695"));
        addBook(new Book("Malcom x", "Alex Haley", 1965,"9789067282842"));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return List.copyOf(books);// copy of books to prevent changes!
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> searchBook(String title, String author, int publicationYear) {
        return books.stream()
                .filter(b -> b.getTitle().equals(title) & b.getAuthor().equals(author) & (b.getPublicationYear() == publicationYear))
                .findAny();
    }

    public Optional<Book> searchBook(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findAny();
    }

    public Optional<Book> searchBook(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findAny();
    }

    public int countAllCopies(String isbn) {
        return (int) books.stream()
                .filter(b -> Objects.nonNull(b.getIsbn()))
                .filter(b -> b.getIsbn().equals(isbn))
                .count();
    }

    // assertive: missing info-> no copies of each other!
    public int countAllCopies(String title, String author, int publicationYear) {
        return (int) books.stream()
                .filter(b -> Objects.nonNull(b.getTitle()) && Objects.nonNull(b.getAuthor()) && b.getPublicationYear() > 0 )
                .filter(b -> b.getTitle().equals(title)
                        & b.getAuthor().equals(author)
                        & b.getPublicationYear() == (publicationYear)  )
                .count();
    }

}
