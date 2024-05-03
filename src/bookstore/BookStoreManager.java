package bookstore;

import model.Book;
import model.Sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Singleton class for managing the book store
public class BookStoreManager {
    private static BookStoreManager instance;
    private Map<String, Book> bookInventory;
    private List<Sale> sales;
    private double totalRevenue;

    private BookStoreManager() {
        bookInventory = new HashMap<>();
        sales = new ArrayList<>();
        totalRevenue = 0;
    }

    // Get the singleton instance
    public static synchronized BookStoreManager getInstance() {
        if (instance == null) {
            instance = new BookStoreManager();
        }
        return instance;
    }

    // Add books to the inventory
    public void addBook(String bookName, String author, double price, int quantity) {
        Book book = new Book(bookName, author, price);
        bookInventory.put(bookName, book);
        bookInventory.get(bookName).setQuantity(quantity);
    }

    // Get the list of books in the inventory
    public Map<String, Book> getBookList() {
        return bookInventory;
    }

    // Sell books from the inventory
    public boolean sellBook(String bookName, int quantity) {
        if (bookInventory.containsKey(bookName) && bookInventory.get(bookName).getQuantity() >= quantity) {
            Book book = bookInventory.get(bookName);
            book.setQuantity(book.getQuantity() - quantity);
            Sale sale = new Sale(book, quantity, LocalDate.now());
            sales.add(sale);
            totalRevenue += book.getPrice() * quantity;
            return true;
        }
        return false;
    }

    // Get the list of sales
    public List<Sale> getSales() {
        return sales;
    }

    // Get total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Main method to run the console application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookStoreManager storeManager = BookStoreManager.getInstance();

        while (true) {
            System.out.print("\033[H\033[2J"); // Clear console
            System.out.flush(); // Flush the console

            System.out.println("Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. View book list");
            System.out.println("3. Sell a book");
            System.out.println("4. View sales");
            System.out.println("5. View total revenue");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the book name: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Enter the author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    storeManager.addBook(bookName, author, price, quantity);
                    System.out.println("Book added successfully!");
                    break;
                case 2:
                    System.out.println("Book List:");
                    Map<String, Book> bookList = storeManager.getBookList();
                    if (bookList.isEmpty()) {
                        System.out.println("No books in the inventory.");
                    } else {
                        for (Map.Entry<String, Book> entry : bookList.entrySet()) {
                            Book book = entry.getValue();
                            System.out.println("Title: " + book.getTitle());
                            System.out.println("Author: " + book.getAuthor());
                            System.out.println("Price: $" + book.getPrice());
                            System.out.println("Quantity: " + book.getQuantity());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the book name to sell: ");
                    String bookToSell = scanner.nextLine();
                    System.out.print("Enter the quantity to sell: ");
                    int quantityToSell = scanner.nextInt();
                    boolean sold = storeManager.sellBook(bookToSell, quantityToSell);
                    if (sold) {
                        System.out.println("Book sold successfully!");
                    } else {
                        System.out.println("Failed to sell book! Not enough quantity in inventory.");
                    }
                    break;
                case 4:
                    System.out.println("Sales:");
                    List<Sale> sales = storeManager.getSales();
                    if (sales.isEmpty()) {
                        System.out.println("No sales recorded yet.");
                    } else {
                        for (Sale sale : sales) {
                            System.out.println("Book: " + sale.getBook().getTitle() + ", Quantity: " + sale.getQuantity() + ", Date: " + sale.getDate());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Total Revenue: $" + storeManager.getTotalRevenue());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
