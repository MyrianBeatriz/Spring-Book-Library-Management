package com.oreilly.library.services;

import com.oreilly.library.dao.BookRepository;
import com.oreilly.library.entities.Book;  // Add this import statement
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void initializeDatabase() {
        // Don't need this (initialization in src/main/resources/data.sql)
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAllInBatch();
    }

    // Additional method for finding books by specific criteria
    @Transactional(readOnly = true)
    public List<Book> findAllBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }
}
