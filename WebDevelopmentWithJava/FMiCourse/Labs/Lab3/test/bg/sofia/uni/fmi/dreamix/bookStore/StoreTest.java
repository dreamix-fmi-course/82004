package bg.sofia.uni.fmi.dreamix.bookStore;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private final BookStore bookStore = new BookStore();

    private static final Book cleanCodeBook = new Book("Clean Code_ A Handbook of Agile Software Craftsmanship",
            "Robert C. Martin", BigDecimal.valueOf(43.49), "Kindle",
            LocalDate.of(2008, 5, 13));

    private static final Book springBootBook = new Book("Spring Boot Primer_ An introductory book to not regret",
            "Tatsuya Tamura", BigDecimal.valueOf(9.99), "Kindle",
            LocalDate.of(2018, 4, 16));

    private static final Book workingEffectivelyWithLegacyCode = new Book("Working effectively with legacy code",
            "Feathers", BigDecimal.valueOf(58.49), "Prentice Hall",
            LocalDate.of(2013, 10, 11));


    @BeforeEach
    void setUp() {
        bookStore.add(workingEffectivelyWithLegacyCode);

        bookStore.add(springBootBook);

        bookStore.add(cleanCodeBook);
    }

    @AfterEach
    void tearDown() {
        bookStore.clear();
    }

    @Test
    void testAddWithBeforeAllInput() {
        List<Book> listOfAllNewlyAddedBooks = new LinkedList<>();
        listOfAllNewlyAddedBooks.add(springBootBook);

        assertEquals(bookStore.getAllBooksPublishedAfter(
                        LocalDate.of(2017, 10, 10)).size(),
                listOfAllNewlyAddedBooks.size());

        assertTrue(bookStore.getAllBooksPublishedAfter(
                        LocalDate.of(2017, 10, 10))
                .containsAll(listOfAllNewlyAddedBooks));
    }

    @Test
    void testAddWithValidBook() {
        Book proGitBook = new Book("Pro git - second eddition",
                "Scott Chacon and Ben Straub", new BigDecimal(0.0),
                "Apress", LocalDate.of(2022, 1, 3));
        bookStore.add(proGitBook);

        List<Book> listOfAllNewlyAddedBooks = new LinkedList<>();
        listOfAllNewlyAddedBooks.add(proGitBook);

        assertEquals(listOfAllNewlyAddedBooks.size(),
                bookStore.getAllBooksPublishedAfter(
                        LocalDate.of(2021, 10, 10)).size());

        assertTrue(listOfAllNewlyAddedBooks.containsAll(bookStore.getAllBooksPublishedAfter(
                LocalDate.of(2021, 10, 10))));
    }

    @Test
    void testRemoveWithNullBook() {
        assertThrows(IllegalArgumentException.class,
                () -> bookStore.remove(null));
    }

    @Test
    void testRemoveWithValidBook() {
        bookStore.remove(workingEffectivelyWithLegacyCode);

        List<Book> listOfRemainingBookAfterRemoval = new LinkedList<>();
        listOfRemainingBookAfterRemoval.add(springBootBook);
        listOfRemainingBookAfterRemoval.add(cleanCodeBook);

        assertEquals(listOfRemainingBookAfterRemoval.size(),
                bookStore.getAllBooksPublishedAfter(LocalDate.of(1000, 10, 10)).size());
        assertTrue(listOfRemainingBookAfterRemoval.containsAll(
                bookStore.getAllBooksPublishedAfter(LocalDate.of(1000, 10, 10))));
    }

    @Test
    void testRemoveWithNonExistingBook() {
        Book proGitBook = new Book("Pro git - second eddition",
                "Scott Chacon and Ben Straub", new BigDecimal(0.0),
                "Apress", LocalDate.of(2022, 1, 3));

        bookStore.remove(proGitBook);

        List<Book> listOfRemainingBookAfterRemoval = new LinkedList<>();
        listOfRemainingBookAfterRemoval.add(springBootBook);
        listOfRemainingBookAfterRemoval.add(cleanCodeBook);
        listOfRemainingBookAfterRemoval.add(workingEffectivelyWithLegacyCode);

        assertEquals(listOfRemainingBookAfterRemoval.size(),
                bookStore.getAllBooksPublishedAfter(LocalDate.of(1000, 10, 10)).size());
        assertTrue(listOfRemainingBookAfterRemoval.containsAll(
                bookStore.getAllBooksPublishedAfter(LocalDate.of(1000, 10, 10))));
    }

    @Test
    void testGetAllBooksByAuthorWithInvalidAuthorInput() {
        assertThrows(IllegalArgumentException.class,
                () -> bookStore.getAllBooksByAuthor(null));
        assertThrows(IllegalArgumentException.class,
                () -> bookStore.getAllBooksByAuthor(""));
    }

    @Test
    void testGetAllBooksByAuthorWithValidAuthorInput() {
        List<Book> allBooksByFeathers = new LinkedList<>();

        allBooksByFeathers.add(workingEffectivelyWithLegacyCode);

        assertEquals(allBooksByFeathers.size(),
                bookStore.getAllBooksByAuthor("Feathers").size());

        assertTrue(allBooksByFeathers
                .containsAll(bookStore.getAllBooksByAuthor("Feathers")));
    }

    @Test
    void testGetAllBooksPublishedAfterWithInvalidYearInput() {
        assertThrows(IllegalArgumentException.class,
                () -> bookStore.getAllBooksPublishedAfter(null));
    }

    @Test
    void testGetAllBooksPublishedAfterWithValidYearInput() {
        List<Book> listOfAllBooksAfter2020 = new LinkedList<>();

        listOfAllBooksAfter2020.add(springBootBook);

        assertEquals(listOfAllBooksAfter2020.size(),
                bookStore.getAllBooksPublishedAfter(LocalDate.of(2017, 2, 20)).size());

        assertTrue(listOfAllBooksAfter2020.containsAll(
                bookStore.getAllBooksPublishedAfter(LocalDate.of(2017, 2, 20))));

    }

    @Test
    void testGetAllBooksBetweenWithInvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> bookStore.getAllBooksBetween(null, LocalDate.now()));

        assertThrows(IllegalArgumentException.class,
                () -> bookStore.getAllBooksBetween(LocalDate.now(), null));
    }

    @Test
    void testGetAllBooksBetweenWithValidInput() {
        List<Book> listOfAllBooksAfter2017AndBefore2020 = new LinkedList<>();

        listOfAllBooksAfter2017AndBefore2020.add(springBootBook);

        assertEquals(listOfAllBooksAfter2017AndBefore2020.size(),
                bookStore.getAllBooksBetween(
                        LocalDate.of(2017, 2, 20),
                        LocalDate.of(2020,2,20)).size());

        assertTrue(listOfAllBooksAfter2017AndBefore2020.containsAll(
                bookStore.getAllBooksBetween(
                        LocalDate.of(2017, 2, 20),
                        LocalDate.of(2020,2,20))));

    }

    @Test
    void testClear() {
        bookStore.clear();

        List<Book> listOfBookRemainingAfterClean = new LinkedList<>();

        assertTrue(listOfBookRemainingAfterClean
                .containsAll(bookStore.getAllBooksPublishedAfter(LocalDate.of(1000,10,10))));
    }

    @Test
    void testGetAllBooksGroupByPublisher() {
        Map<String, List<Book>> mapOfAllBookGroupedByPublishers = new HashMap<>();

        mapOfAllBookGroupedByPublishers.put(cleanCodeBook.getPublisher(), List.of(cleanCodeBook));
        mapOfAllBookGroupedByPublishers.put(workingEffectivelyWithLegacyCode.getPublisher(), List.of(workingEffectivelyWithLegacyCode));
        mapOfAllBookGroupedByPublishers.put(springBootBook.getPublisher(), List.of(springBootBook));

        assertEquals(mapOfAllBookGroupedByPublishers.size(),
                bookStore.getAllBooksGroupByPublisher().size());
    }

    @Test
    void testGetAllBooksFilterAuthorFeathers() {
        List<Book> listOfBooksByAuthorFeathers = new LinkedList<>();

        listOfBooksByAuthorFeathers.add(workingEffectivelyWithLegacyCode);

        assertEquals(listOfBooksByAuthorFeathers.size(),
                bookStore.getAllBooksFilterBy(book -> book.getAuthor().equals("Feathers")).size());
        assertTrue(listOfBooksByAuthorFeathers.containsAll(
                bookStore.getAllBooksFilterBy(book -> book.getAuthor().equals("Feathers"))));
    }
}