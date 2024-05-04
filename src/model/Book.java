package model;

// Book entity class representing a book
public class Book {
    private int bookID;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private String description;

    public Book(int bookID, String title, String author, double price, String description) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
    }

    // Getters and setters for book attributes
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
