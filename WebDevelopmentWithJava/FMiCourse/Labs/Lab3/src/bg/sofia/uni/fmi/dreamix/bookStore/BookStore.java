package bg.sofia.uni.fmi.dreamix.bookStore;

import bg.sofia.uni.fmi.dreamix.repository.StoreDB;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookStore implements Store {
    private final StoreDB booksDataStorage = StoreDB.getInstance();

    @Override
    public boolean add(Book o) {
        if (o == null) {
            throw new IllegalArgumentException("The value of argument o of " +
                    "method add cannot be null!");
        }

        if (booksDataStorage.getALl().contains(o)) {
            return false;
        }

        booksDataStorage.add(o);
        return true;
    }

    @Override
    public void remove(Book o) {
        if (o == null) {
            throw new IllegalArgumentException("The value of argument o of " +
                    "method remove cannot be null!");
        }

        booksDataStorage.remove(o.getISBN());
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("The value of argument author " +
                    "in method connect cannot be empty string or null");
        }

        return booksDataStorage.getALl().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksPublishedAfter(LocalDate from) {
        if (from == null) {
            throw new IllegalArgumentException("The value of argument from in method " +
                    "cannot be null");
        }

        return booksDataStorage.getALl().stream()
                .filter(book -> book.getPublishedYear().isAfter(from))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksBetween(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("The value of a LocalDate " +
                "param cannot be null!");
        }

        return booksDataStorage.getALl().stream()
                .filter(book -> book.getPublishedYear().isAfter(from))
                .filter(book -> book.getPublishedYear().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Book>> getAllBooksGroupByAuthor() {
        return booksDataStorage.getALl().stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
    }

    @Override
    public Map<String, List<Book>> getAllBooksGroupByPublisher() {
        return booksDataStorage.getALl().stream()
                .collect(Collectors.groupingBy(Book::getPublisher));

    }

    @Override
    public List<Book> getAllBooksFilterBy(Predicate<Book> bookPredicate) {
        return booksDataStorage.getALl().stream().filter(bookPredicate).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        booksDataStorage.clear();
    }
}
