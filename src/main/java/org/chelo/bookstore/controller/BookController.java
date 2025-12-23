package org.chelo.bookstore.controller;

import org.chelo.bookstore.DTOs.CreateBookRequest;
import org.chelo.bookstore.model.Book;
import org.chelo.bookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book create(@RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody CreateBookRequest book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PostMapping("{id}/sell")
    public ResponseEntity<String> sellBook(@PathVariable Long id, @RequestParam int amount) {
        bookService.sellBook(id, amount);
        return ResponseEntity.ok("Book sold.");
    }
}
