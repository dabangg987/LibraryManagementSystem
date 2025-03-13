package com.example.LMS.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LMS.entity.Book;
import com.example.LMS.entity.BookIssue;
import com.example.LMS.entity.Student;
import com.example.LMS.errorHandler.BookAlreadyIssuedException;
import com.example.LMS.errorHandler.CustomException;
import com.example.LMS.errorHandler.ResourceNotFoundException;
import com.example.LMS.repository.BookIssueRepository;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.repository.StudentRepository;

@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookIssueService(BookIssueRepository bookIssueRepository, BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    // ✅ Issue a book
    public BookIssue issueBook(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + studentId + " not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " not found"));

        if (book.getbStatus().equalsIgnoreCase("Issued")) {
            throw new BookAlreadyIssuedException("Book is already issued");
        }

        // ✅ Check if student already issued 4 books in the current month
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        long issuedCount = bookIssueRepository.countByStudentAndIssueDateAfter(student, oneMonthAgo);

        if (issuedCount >= 4) {
            throw new CustomException("Student has already issued 4 books this month");
        }

        // ✅ Issue the book
        BookIssue bookIssue = new BookIssue();
        bookIssue.setBook(book);
        bookIssue.setStudent(student);
        bookIssue.setIssueDate(LocalDateTime.now());
        bookIssue.setReturnDate(LocalDateTime.now().plusDays(1));
        bookIssue.setStatus("Issued"); // ✅ Set status

        // ✅ Update book status directly in the Book entity
        book.setbStatus("Issued");
        bookRepository.save(book);

        return bookIssueRepository.save(bookIssue);
    }

    // ✅ Return a book
    public BookIssue returnBook(Long issueId) {
        BookIssue bookIssue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Book Issue not found with ID: " + issueId));

        // ✅ Update status on return
        bookIssue.getBook().setbStatus("Available");
        bookIssue.setStatus("Returned");

        return bookIssueRepository.save(bookIssue);
    }

    // ✅ Get all issued books
    public List<BookIssue> getAllIssuedBooks() {
        return bookIssueRepository.findAll();
    }

    // ✅ Get issued books by student
    public List<BookIssue> getIssuedBooksByStudent(Long studentId) {
        return bookIssueRepository.findByStudent_sId(studentId);
    }
}
