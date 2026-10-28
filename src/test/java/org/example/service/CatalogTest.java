package org.example.service;

import org.example.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    private Catalog catalog;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        catalog = new Catalog();

        book1 = new Book(
                1,
                "Effective Java",
                "Joshua Bloch"
        );

        book2 = new Book(
                2,
                "Clean Code",
                "Robert Martin"
        );

        catalog.addBook(book1);
        catalog.addBook(book2);
    }

    @Test
    void shouldAddBook() {
        assertEquals(2, catalog.getBooks().size());
        assertTrue(catalog.getBooks().contains(book1));
    }

    @Test
    void shouldRejectDuplicateBookId() {
        Book duplicate = new Book(
                1,
                "Different title",
                "Another author"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> catalog.addBook(duplicate)
        );

        assertEquals(2, catalog.getBooks().size());
    }

    @Test
    void shouldRejectNullBook() {
        assertThrows(
                NullPointerException.class,
                () -> catalog.addBook(null)
        );
    }

    @Test
    void shouldIssueBook() {
        Catalog.IssueRecord record =
                catalog.issueBook(1, "Ivanov");

        assertEquals(book1, record.getBook());
        assertEquals("Ivanov", record.getReaderName());
        assertEquals(LocalDate.now(), record.getIssueDate());
        assertNull(record.getReturnDate());
        assertFalse(record.isReturned());
    }

    @Test
    void shouldRejectIssueOfUnknownBook() {
        assertThrows(
                IllegalArgumentException.class,
                () -> catalog.issueBook(999, "Ivanov")
        );
    }

    @Test
    void shouldRejectEmptyReaderName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> catalog.issueBook(1, " ")
        );
    }

    @Test
    void shouldNotIssueBookTwiceWithoutReturn() {
        catalog.issueBook(1, "Ivanov");

        assertThrows(
                IllegalStateException.class,
                () -> catalog.issueBook(1, "Petrov")
        );

        assertEquals(1, catalog.getIssueHistory().size());
    }

    @Test
    void shouldReturnBook() {
        Catalog.IssueRecord record =
                catalog.issueBook(1, "Ivanov");

        catalog.returnBook(1);

        assertTrue(record.isReturned());
        assertEquals(LocalDate.now(), record.getReturnDate());
    }

    @Test
    void shouldRejectReturnOfNotIssuedBook() {
        assertThrows(
                IllegalStateException.class,
                () -> catalog.returnBook(1)
        );
    }

    @Test
    void shouldPreserveIssueHistoryAfterReturn() {
        catalog.issueBook(1, "Ivanov");

        catalog.returnBook(1);

        assertEquals(1, catalog.getIssueHistory().size());
        assertTrue(
                catalog.getIssueHistory().get(0).isReturned()
        );
    }

    @Test
    void shouldAllowRepeatedIssueAfterReturn() {
        catalog.issueBook(1, "Ivanov");
        catalog.returnBook(1);

        Catalog.IssueRecord secondRecord =
                catalog.issueBook(1, "Petrov");

        assertEquals(2, catalog.getIssueHistory().size());
        assertEquals("Petrov", secondRecord.getReaderName());
        assertFalse(secondRecord.isReturned());
    }

    @Test
    void shouldFindHistoryByReaderUsingAnonymousClass() {
        catalog.issueBook(1, "Ivanov");
        catalog.issueBook(2, "Petrov");

        catalog.returnBook(1);

        List<Catalog.IssueRecord> result =
                catalog.getHistoryByReader("ivanov");

        assertEquals(1, result.size());
        assertEquals(
                "Ivanov",
                result.get(0).getReaderName()
        );
        assertTrue(result.get(0).isReturned());
    }

    @Test
    void shouldReturnEmptyHistoryForUnknownReader() {
        catalog.issueBook(1, "Ivanov");

        assertTrue(
                catalog.getHistoryByReader("Unknown").isEmpty()
        );
    }

    @Test
    void shouldReturnOnlyActiveIssues() {
        catalog.issueBook(1, "Ivanov");
        catalog.issueBook(2, "Petrov");

        catalog.returnBook(1);

        List<Catalog.IssueRecord> activeIssues =
                catalog.getActiveIssues();

        assertEquals(1, activeIssues.size());
        assertEquals(
                2,
                activeIssues.get(0).getBook().getId()
        );
    }

    @Test
    void shouldNotAllowModificationOfBooksList() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> catalog.getBooks().clear()
        );
    }

    @Test
    void shouldNotAllowModificationOfHistoryList() {
        catalog.issueBook(1, "Ivanov");

        assertThrows(
                UnsupportedOperationException.class,
                () -> catalog.getIssueHistory().clear()
        );
    }
}