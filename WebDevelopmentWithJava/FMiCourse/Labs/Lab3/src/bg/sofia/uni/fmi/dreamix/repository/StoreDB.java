package bg.sofia.uni.fmi.dreamix.repository;

import bg.sofia.uni.fmi.dreamix.bookStore.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StoreDB {
    private static StoreDB instance = new StoreDB();

    private Map<String, Book> booksDataBase = new HashMap<>();

    private StoreDB() {
    }

    public static StoreDB getInstance() {
        return instance;
    }

    public void add(Book newBook) {
        if (newBook == null) {
            throw new IllegalArgumentException("The value of argument newBook in method" +
                    "add cannot be null!");
        }


        booksDataBase.put(newBook.getISBN(), newBook);
    }

    public Book getByISBN(String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new IllegalArgumentException("The value of argument ISBN in method " +
                    "getByISBN cannot be null or empty string!");
        }

        return booksDataBase.get(ISBN);
    }

    public void update(Book book) {
        Book templateBookFromDB = getByISBN(book.getISBN());

        if (!book.equals(templateBookFromDB)) {
            booksDataBase.put(book.getISBN(), book);
        }
    }

    public void remove(String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new IllegalArgumentException("The value of argument ISBN in method " +
                    "remove cannot be null or empty string!");
        }

        booksDataBase.remove(ISBN);
    }

    public List<Book> getALl() {
        return booksDataBase.values().stream().toList();
    }

    public void clear() {
        booksDataBase.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDB storeDB = (StoreDB) o;
        return Objects.equals(booksDataBase, storeDB.booksDataBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booksDataBase);
    }
}
