package com.example.LMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LMS.entity.Book;
import com.example.LMS.errorHandler.ResourceNotFoundException;
import com.example.LMS.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }

    // Update book status
    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBookById(id);
        book.setbName(updatedBook.getbName());
        book.setbStatus(updatedBook.getbStatus());
        return bookRepository.save(book);
    }

    // Delete book by ID
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}
