package com.example.LMS.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bId")
    @JsonBackReference("book-bookIssue")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "sId")
    @JsonBackReference("student-bookIssue")
    private Student student;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime issueDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnDate;
    
    private String status;

    // Constructor
    public BookIssue() {
    }

    // Getters and Setters
    public Long getBiId() {
        return biId;
    }

    public void setBiId(Long biId) {
        this.biId = biId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
