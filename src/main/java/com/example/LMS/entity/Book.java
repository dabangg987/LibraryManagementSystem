package com.example.LMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bId;

    private String bName;
    private String bStatus;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference("book-bookIssue")
    private List<BookIssue> bookIssues;

    // Constructor
    public Book() {
    }

    // Getters and Setters
    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbStatus() {
        return bStatus;
    }

    public void setbStatus(String bStatus) {
        this.bStatus = bStatus;
    }

	public List<BookIssue> getBookIssues() {
		return bookIssues;
	}

	public void setBookIssues(List<BookIssue> bookIssues) {
		this.bookIssues = bookIssues;
	}

    
}
