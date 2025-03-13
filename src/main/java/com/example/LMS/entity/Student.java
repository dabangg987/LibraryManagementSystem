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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId;

    private String sName;
    private String sAddress;
    private String mobile;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference("student-bookIssue")
    private List<BookIssue> bookIssues;

    // Getters and Setters
    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<BookIssue> getBookIssues() {
        return bookIssues;
    }

    public void setBookIssues(List<BookIssue> bookIssues) {
        this.bookIssues = bookIssues;
    }
}
