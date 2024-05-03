package model;

import java.time.LocalDate;

// Sale entity class representing a book sale transaction
public class Sale {
    private Book book;
    private int quantity;
    private LocalDate date;

    public Sale(Book book, int quantity, LocalDate date) {
        this.book = book;
        this.quantity = quantity;
        this.date = date;
    }

    // Getters and setters for sale attributes
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}