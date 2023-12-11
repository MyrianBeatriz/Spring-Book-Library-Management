package com.oreilly.library.controllers;

import com.oreilly.library.entities.Book;
import com.oreilly.library.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getBooks() {
        return service.findAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.of(service.findBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book b = service.saveBook(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(b.getId())
                .toUri();
        return ResponseEntity.created(location).body(b);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.findBookById(id)
                .map(b -> {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setIsbn(book.getIsbn());
                    b.setPublicationDate(book.getPublicationDate());
                    return ResponseEntity.ok(service.saveBook(b));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return service.findBookById(id)
                .map(b -> {
                    service.deleteBook(b);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // You can add additional methods as needed

}



