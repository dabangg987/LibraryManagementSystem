package com.example.LMS.repository;

import com.example.LMS.entity.BookIssue;
import com.example.LMS.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    // Count the number of books issued to a student in a given month
    @Query("SELECT COUNT(bi) FROM BookIssue bi WHERE bi.student.sId = :studentId AND MONTH(bi.issueDate) = MONTH(:date) AND YEAR(bi.issueDate) = YEAR(:date)")
    int countBooksIssuedByStudentInMonth(Long studentId, LocalDate date);
    
    long countByStudentAndIssueDateAfter(Student student, LocalDateTime issueDate);

    // Find issued books by a specific student
    List<BookIssue> findByStudent_sId(Long studentId);

    // Find issued books by book ID
    List<BookIssue> findByBook_bId(Long bookId);
    

//	long countByStudentAndIssueDateAfter(Student student, LocalDateTime oneMonthAgo);
}
