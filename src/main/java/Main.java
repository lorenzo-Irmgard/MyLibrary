import enums.Commands;
import model.Book;
import service.Library;
import service.filters.AuthorFilter;
import service.filters.GenreFilter;
import service.filters.TitleFilter;
import service.filters.YearFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            try {
                System.out.println("""
                        \n
                        Write \'add\' for add Book
                        Write \'remove\' for Remove Book
                        Write \'find\' for Search Book
                        Write \'list\' for List All Books
                        Write \'exit\' for Exit
                        """);
                String choice = scanner.nextLine();

                switch (Commands.valueOf(choice.toUpperCase())) {
                    case Commands.ADD -> {
                        System.out.println("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.println("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.println("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        library.addBook(new Book(title, author, genre, year));
                    }
                    case Commands.REMOVE -> {
                        System.out.println("Enter title: ");
                        String titleToRemove = scanner.nextLine();
                        library.removeBook(titleToRemove);
                    }
                    case Commands.FIND -> {
                        System.out.println("""
                        \n
                        Write \'title\' for search by title
                        Write \'author\' for search by author
                        Write \'genre\' for search by genre
                        Write \'year\' for search by year
                        """);
                        List<Book> results = new ArrayList<>();
                        boolean choosing = true;
                        while (choosing) {
                            String searchChoice = scanner.nextLine();
                            switch (searchChoice.toLowerCase()) {
                                case "title" -> {
                                    System.out.println("Enter title to search:");
                                    String titleToSearch = scanner.nextLine();
                                    results = library.search(new TitleFilter(titleToSearch));
                                    choosing = false;
                                }
                                case "author" -> {
                                    System.out.println("Enter author to search:");
                                    String authorToSearch = scanner.nextLine();
                                    results = library.search(new AuthorFilter(authorToSearch));
                                    choosing = false;
                                }
                                case "genre" -> {
                                    System.out.println("Enter genre to search:");
                                    String genreToSearch = scanner.nextLine();
                                    results = library.search(new GenreFilter(genreToSearch));
                                    choosing = false;
                                }
                                case "year" -> {
                                    System.out.println("Enter year to search:");
                                    int yearToSearch = Integer.parseInt(scanner.nextLine());
                                    results = library.search(new YearFilter(yearToSearch));
                                    choosing = false;
                                }
                                default -> System.out.println("No such menu option");
                            }
                        }
                        results.forEach(System.out::println);
                    }
                    case Commands.LIST -> library.listBooks().forEach(System.out::println);
                    case Commands.EXIT -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Unrecognized command");
            }
        }
        scanner.close();
    }
}