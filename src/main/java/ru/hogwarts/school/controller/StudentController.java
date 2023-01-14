package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }
    @PutMapping("{id}")
    public Student update(@PathVariable long id,
                          @RequestBody Student student) {
        return service.update(id, student);

    }
    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student findStudent = service.read(id);
        if (findStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findStudent);
    }
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        service.deleteStudent(id);
    }
    @GetMapping
    public Collection<Student> studentsTheSameAge(@RequestParam int age) {
        return service.findByAge(age);
    }
    @GetMapping(params = {"min, max"})
    public Collection<Student> allStudentsByAgeBetween(@RequestParam int min,
                                                       @RequestParam int max) {
        return service.allStudentsByAgeBetween(min, max);
    }
    @GetMapping("/{id}/Faculty")
    public Faculty getStudentFaculty(@PathVariable long id) {
        return service.getStudentFaculty(id);
    }
}
