package model;

import java.util.concurrent.atomic.AtomicLong;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private boolean isSoftDeleted;
    private final Long id;
    //- totalCopies: int -> service
    // availableCopies: int -> service

    static final AtomicLong atomicLongBook = new AtomicLong(1);

    public Book(String title) {
        this(title, "", 0, "");
    }

    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.id = atomicLongBook.getAndIncrement();
        this.isSoftDeleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
        isSoftDeleted = softDeleted;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                ", isSoftDeleted='" + isSoftDeleted + '\'' +
                ", id='" + id + '\'' +
                "}";
    }

}
