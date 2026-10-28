package org.example.entity;

import java.util.Objects;

public class Book {
    private final int id;
    private final String title;
    private final String author;

    public Book(int id, String title, String author) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID книги должен быть неотрицательным"
            );
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Название книги не может быть пустым"
            );
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException(
                    "Автор книги не может быть пустым"
            );
        }

        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book other)) {
            return false;
        }

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}