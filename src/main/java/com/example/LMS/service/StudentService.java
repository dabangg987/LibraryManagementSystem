package com.example.LMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LMS.entity.Student;
import com.example.LMS.errorHandler.ResourceNotFoundException;
import com.example.LMS.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }

    // Update student details
    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = getStudentById(id);
        student.setsName(updatedStudent.getsName());
        student.setsAddress(updatedStudent.getsAddress());
        student.setMobile(updatedStudent.getMobile());
        return studentRepository.save(student);
    }

    // Delete student by ID
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
