package com.example.LMS.controller;

import com.example.LMS.entity.BookIssue;
import com.example.LMS.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-issues")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    // ✅ Issue a book
    @PostMapping("/issue/{studentId}/{bookId}")
    public ResponseEntity<BookIssue> issueBook(@PathVariable Long studentId, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookIssueService.issueBook(studentId, bookId));
    }

    // ✅ Return a book
    @PostMapping("/return/{issueId}")
    public ResponseEntity<BookIssue> returnBook(@PathVariable Long issueId) {
        return ResponseEntity.ok(bookIssueService.returnBook(issueId));
    }

    // ✅ Get all issued books
    @GetMapping
    public ResponseEntity<List<BookIssue>> getAllIssuedBooks() {
        return ResponseEntity.ok(bookIssueService.getAllIssuedBooks());
    }

    // ✅ Get books issued by a specific student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BookIssue>> getIssuedBooksByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(bookIssueService.getIssuedBooksByStudent(studentId));
    }
}
