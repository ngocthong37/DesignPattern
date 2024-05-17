package model;

public class Registration {
    private User user;
    private Book book;
    private RegistrationStatus status;

    public Registration(User user, Book book, RegistrationStatus status) {
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User: " + user + ", Book: " + book + ", Status: " + status;
    }
}
