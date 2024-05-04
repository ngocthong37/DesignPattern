package bookstore;

import model.Book;
import model.Sale;

import java.time.LocalDate;
import java.util.*;

public class BookStoreFactory {
    private final Map<Integer, Book> bookInventory;
    private int nextBookID;
    private List<Sale> sales;
    private double totalRevenue;
     public BookStoreFactory() {
        this.bookInventory = new HashMap<>();
         this.sales = new ArrayList<>(); // Khởi tạo sales ở đây
         totalRevenue = 0;
        nextBookID = 1;
    }

    // Method to add a book to the list
    public void addBook(String title, String author, double price, int quantity, String description) {
        Book book = new Book(nextBookID++, title, author, price, description);
        book.setQuantity(quantity);
        bookInventory.put(book.getBookID(), book);
    }

    // Get the list of books in the inventory
    public Map<Integer, Book> getBookList() {
        return bookInventory;
    }
    // Sell books from the inventory
    public boolean sellBook(int bookID, int quantity) {
        if (bookInventory.containsKey(bookID) && bookInventory.get(bookID).getQuantity() >= quantity) {
            Book book = bookInventory.get(bookID);
            book.setQuantity(book.getQuantity() - quantity);
            Sale sale = new Sale(book, quantity, LocalDate.now());
            sales.add(sale);
            totalRevenue += book.getPrice() * quantity;
            return true;
        }
        return false;
    }
    public List<Sale> getSales() {
        return sales;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public static void main(String[] args) {
        // Tạo một đối tượng BookFactory
        Scanner scanner = new Scanner(System.in);
        BookStoreFactory factory = new BookStoreFactory();

        // Thêm các cuốn sách vào danh sách
        factory.addBook("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 20, "A classic American novel.");
        factory.addBook("To Kill a Mockingbird", "Harper Lee", 12.50, 15, "A Pulitzer Prize-winning novel.");
        factory.addBook("1984", "George Orwell", 9.99, 25, "A dystopian novel.");
        factory.addBook("Pride and Prejudice", "Jane Austen", 8.75, 18, "A romantic novel.");
        factory.addBook("The Catcher in the Rye", "J.D. Salinger", 11.25, 12, "A coming-of-age novel.");
        // Lấy danh sách sách từ BookFactory
        while (true) {
            System.out.print("\033[H\033[2J"); // Clear console
            System.out.flush(); // Flush the console
            System.out.println("Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. View book list");
            System.out.println("3. Sell a book");
            System.out.println("4. View sales");
            System.out.println("5. View total revenue");
            System.out.println("6. Find a book by name");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the description: ");
                    String description = scanner.nextLine();
                    factory.addBook(title, author, price, quantity, description);
                    System.out.println("Book added successfully!");
                    break;
                case 2:
                    System.out.println("Book List:");
                    Map<Integer, Book> bookList = factory.getBookList();
                    if (bookList.isEmpty()) {
                        System.out.println("No books in the inventory.");
                    } else {
                        for (Map.Entry<Integer, Book> entry : bookList.entrySet()) {
                            Book book = entry.getValue();
                            System.out.println("ID: " + book.getBookID());
                            System.out.println("Title: " + book.getTitle());
                            System.out.println("Author: " + book.getAuthor());
                            System.out.println("Price: $" + book.getPrice());
                            System.out.println("Quantity: " + book.getQuantity());
                            System.out.println("Description: " + book.getDescription());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the book ID to sell: ");
                    int bookID = scanner.nextInt();
                    System.out.print("Enter the quantity to sell: ");
                    int quantityToSell = scanner.nextInt();
                    boolean sold = factory.sellBook(bookID, quantityToSell);
                    if (sold) {
                        System.out.println("Book sold successfully!");
                    } else {
                        System.out.println("Failed to sell book! Not enough quantity in inventory or invalid book ID.");
                    }
                    break;
                case 4:
                    System.out.println("Sales:");
                    List<Sale> sales = factory.getSales();
                    if (sales.isEmpty()) {
                        System.out.println("No sales recorded yet.");
                    } else {
                        for (Sale sale : sales) {
                            System.out.println("Book: " + sale.getBook().getTitle() + ", Quantity: " + sale.getQuantity() + ", Date: " + sale.getDate());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Total Revenue: $" + factory.getTotalRevenue());
                    break;
                case 6:
                    System.out.print("Enter the title of the book to search: ");
                    String searchTitle = scanner.nextLine();
                    bookList = factory.getBookList();
                    boolean found = false;
                    for (Map.Entry<Integer, Book> entry : bookList.entrySet()) {
                        Book book = entry.getValue();
                        if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                            System.out.println("Book found:");
                            System.out.println("ID: " + book.getBookID());
                            System.out.println("Title: " + book.getTitle());
                            System.out.println("Author: " + book.getAuthor());
                            System.out.println("Price: $" + book.getPrice());
                            System.out.println("Quantity: " + book.getQuantity());
                            System.out.println("Description: " + book.getDescription());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book with title '" + searchTitle + "' not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }

    }


}
