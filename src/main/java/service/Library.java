package service;

import model.Book;
import service.filters.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (!books.contains(book)) books.add(book); // БАГ#1 ИСПРАВЛЕНИЕ: я добавил if здесь
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
    }
    //Сюда подаются фильтры, написанные для каждого поля класса Book.
    public List<Book> search(Filter filter) {
        return books.stream()
                .filter(filter::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }
}