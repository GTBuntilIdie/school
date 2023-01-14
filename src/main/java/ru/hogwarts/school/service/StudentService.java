package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }
    public Student read(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    public Student update(long id, Student student) {
        Student oldStudent = read(id);
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        return studentRepository.save(oldStudent);
    }
    public Student deleteStudent(long id) {
        Student student = read(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Collection<Student> findByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> allStudentsByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Faculty getStudentFaculty(long id) {
        return read(id).getFaculty();
    }



}
