package bookstore;

import model.Book;
import model.Registration;
import model.RegistrationStatus;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private List<Registration> registrations;

    public LibraryManagementSystem() {
        registrations = new ArrayList<>();
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void displayRegistrations() {
        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user information:");
        System.out.print("Name: ");
        String userName = scanner.nextLine();
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        User user = new User(userName, userId);

        System.out.println("Enter book information:");
        System.out.print("Title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Author: ");
        String bookAuthor = scanner.nextLine();
        System.out.print("ISBN: ");
        String bookIsbn = scanner.nextLine();
        Book book = new Book(bookTitle, bookAuthor, bookIsbn);

        System.out.println("Enter registration status:");
        System.out.print("Status (Pending/Approved/Denied): ");
        String statusStr = scanner.nextLine();
        RegistrationStatus status = new RegistrationStatus(statusStr);

        Registration registration = new Registration(user, book, status);
        system.addRegistration(registration);

        System.out.println("\nCurrent Registrations:");
        system.displayRegistrations();

        scanner.close();
    }
}
