import java.util.*;

// Clase Libro
class Book {
    private String title;
    private String author;
    private String isbn;
    private int copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getCopies() { return copies; }

    public void borrowBook() {
        if (copies > 0) {
            copies--;
        } else {
            System.out.println("No copies available.");
        }
    }

    public void returnBook() { copies++; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Copies: " + copies;
    }
}

// Clase Usuario
class Patron {
    private String name;
    private String id;

    public Patron(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public String getId() { return id; }

    @Override
    public String toString() {
        return "Patron: " + name + " (ID: " + id + ")";
    }
}

// Clase Biblioteca
class Library {
    private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void removeBook(String isbn) { books.removeIf(book -> book.getIsbn().equals(isbn)); }
    public void displayBooks() { books.forEach(System.out::println); }
    
    public void registerPatron(Patron patron) { patrons.add(patron); }
    public void viewPatrons() { patrons.forEach(System.out::println); }
    
    public Book searchBook(String query) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(query) || book.getAuthor().equalsIgnoreCase(query) || book.getIsbn().equals(query))
                .findFirst()
                .orElse(null);
    }
}

// Clase principal con el menú
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("1. Add Book\n2. Remove Book\n3. Display Books\n4. Register Patron\n5. View Patrons\n6. Search Book\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter number of copies: ");
                    int copies = scanner.nextInt();
                    library.addBook(new Book(title, author, isbn, copies));
                    break;
                case 2:
                    System.out.print("Enter ISBN of book to remove: ");
                    library.removeBook(scanner.nextLine());
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    System.out.print("Enter patron name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter patron ID: ");
                    String id = scanner.nextLine();
                    library.registerPatron(new Patron(name, id));
                    break;
                case 5:
                    library.viewPatrons();
                    break;
                case 6:
                    System.out.print("Enter book title, author, or ISBN: ");
                    Book foundBook = library.searchBook(scanner.nextLine());
                    System.out.println(foundBook != null ? foundBook : "Book not found.");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
