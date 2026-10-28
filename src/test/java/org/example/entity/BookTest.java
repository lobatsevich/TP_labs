package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void shouldCreateBook() {
        Book book = new Book(
                1,
                "Effective Java",
                "Joshua Bloch"
        );

        assertEquals(1, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
    }

    @Test
    void shouldCompareBooksById() {
        Book first = new Book(1, "Java", "Author A");
        Book second = new Book(1, "Another title", "Author B");
        Book third = new Book(2, "Java", "Author A");

        assertEquals(first, second);
        assertNotEquals(first, third);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldRejectInvalidId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(0, "Java", "Author")
        );
    }

    @Test
    void shouldRejectEmptyTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(1, " ", "Author")
        );
    }

    @Test
    void shouldRejectEmptyAuthor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(1, "Java", "")
        );
    }

    @Test
    void shouldRejectNullTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(1, null, "Author")
        );
    }
}