package com.example.sem5_springdata_ex2.service;

import com.example.sem5_springdata_ex2.domain.Course;
import com.example.sem5_springdata_ex2.domain.Student;
import com.example.sem5_springdata_ex2.repository.CourseRepository;
import com.example.sem5_springdata_ex2.repository.StudentRepository;
import com.example.sem5_springdata_ex2.repository.TrackUserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @TrackUserAction("Create Student")
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @TrackUserAction("Get All Students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @TrackUserAction("Create Course")
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @TrackUserAction("Get All Courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @TrackUserAction("Add Course to Student")
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        student.getCourses().add(course);
        return studentRepository.save(student);
    }
}
