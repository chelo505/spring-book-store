package org.chelo.bookstore.service;

import jakarta.transaction.Transactional;
import org.chelo.bookstore.DTOs.CreateBookRequest;
import org.chelo.bookstore.exception.BookNotFoundException;
import org.chelo.bookstore.exception.InsufficientStockException;
import org.chelo.bookstore.exception.InvalidBookException;
import org.chelo.bookstore.model.Book;
import org.chelo.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(CreateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        validate(book);
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book update(Long id, CreateBookRequest updatedBook) {
        Book book = getById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setQuantity(updatedBook.getQuantity());
        validate(book);
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.delete(getById(id));
    }

    public void validate(Book book) {
        if (book.getPrice() < 0) {
            throw new InvalidBookException("Price can't be less than zero");
        }
        if (book.getQuantity() < 0) {
            throw new InvalidBookException("Quantity can't be less than zero");
        }
    }

    @Transactional
    public void sellBook(Long id, int amount) {
        Book book = getById(id);

        validate(book);

        if (book.getQuantity() < amount) {
            throw new InsufficientStockException("Not enough books in stock");
        }

        book.setQuantity(book.getQuantity() - amount);
        bookRepository.save(book);
    }
}
