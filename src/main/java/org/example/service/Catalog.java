package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Catalog {

    private static final Logger logger = LogManager.getLogger(Catalog.class);

    private final List<Book> books = new ArrayList<>();
    private final List<IssueRecord> issueHistory = new ArrayList<>();

    public static class IssueRecord {
        private final Book book;
        private final String readerName;
        private final LocalDate issueDate;
        private LocalDate returnDate;

        private IssueRecord(Book book, String readerName) {
            this.book = book;
            this.readerName = readerName;
            this.issueDate = LocalDate.now();
            this.returnDate = null;
        }

        public Book getBook() {
            return book;
        }

        public String getReaderName() {
            return readerName;
        }

        public LocalDate getIssueDate() {
            return issueDate;
        }

        public LocalDate getReturnDate() {
            return returnDate;
        }

        public boolean isReturned() {
            return returnDate != null;
        }

        private void markReturned() {
            if (isReturned()) {
                throw new IllegalStateException(
                        "Книга уже была возвращена"
                );
            }

            returnDate = LocalDate.now();
        }

        @Override
        public String toString() {
            return "IssueRecord{" +
                    "book=" + book.getTitle() +
                    ", readerName='" + readerName + '\'' +
                    ", issueDate=" + issueDate +
                    ", returnDate=" + returnDate +
                    '}';
        }
    }

    public void addBook(Book book) {
        Objects.requireNonNull(book, "Книга не может быть null");

        boolean alreadyExists = books.stream()
                .anyMatch(existing ->
                        existing.getId() == book.getId());

        if (alreadyExists) {
            logger.warn(
                    "Книга с ID {} уже существует",
                    book.getId()
            );

            throw new IllegalArgumentException(
                    "Книга с таким ID уже существует"
            );
        }

        books.add(book);

        logger.info(
                "Книга добавлена: {}",
                book.getTitle()
        );
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    public IssueRecord issueBook(int bookId, String readerName) {
        if (readerName == null || readerName.isBlank()) {
            throw new IllegalArgumentException(
                    "Имя читателя не может быть пустым"
            );
        }

        Book book = books.stream()
                .filter(item -> item.getId() == bookId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Книга не найдена"
                        ));

        boolean alreadyIssued = issueHistory.stream()
                .anyMatch(record ->
                        record.getBook().equals(book)
                                && !record.isReturned()
                );

        if (alreadyIssued) {
            logger.warn(
                    "Книга {} уже записана",
                    book.getTitle()
            );

            throw new IllegalStateException(
                    "Книга уже записана"
            );
        }

        IssueRecord record = new IssueRecord(book, readerName);

        issueHistory.add(record);

        logger.info(
                "Книга '{}' записана на {}",
                book.getTitle(),
                readerName
        );

        return record;
    }

    public void returnBook(int bookId) {
        IssueRecord record = issueHistory.stream()
                .filter(item ->
                        item.getBook().getId() == bookId
                                && !item.isReturned()
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Нет активных записей для этой книги"
                        ));

        record.markReturned();

        logger.info(
                "Книга '{}' возвращена {}",
                record.getBook().getTitle(),
                record.getReaderName()
        );
    }

    public List<IssueRecord> getIssueHistory() {
        return List.copyOf(issueHistory);
    }

    public List<IssueRecord> getHistoryByReader(String readerName) {
        Predicate<IssueRecord> readerPredicate =
                new Predicate<>() {
                    @Override
                    public boolean test(IssueRecord record) {
                        return record.getReaderName()
                                .equalsIgnoreCase(readerName);
                    }
                };

        List<IssueRecord> result = issueHistory.stream()
                .filter(readerPredicate)
                .collect(Collectors.toList());

        logger.debug(
                "Найдено {} записей на читателя {}",
                result.size(),
                readerName
        );

        return result;
    }

    public List<IssueRecord> getActiveIssues() {
        return issueHistory.stream()
                .filter(record -> !record.isReturned())
                .collect(Collectors.toList());
    }
}