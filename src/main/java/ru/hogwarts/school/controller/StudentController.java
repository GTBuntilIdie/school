package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;
import java.util.List;


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
    @GetMapping(params = {"min, max"}) // GET http://localhost:8080/student?min=15&max=18
    public Collection<Student> allStudentsByAgeBetween(@RequestParam int min,
                                                       @RequestParam int max) {
        return service.allStudentsByAgeBetween(min, max);
    }
    @GetMapping("/{id}/Faculty")
    public Faculty getStudentFaculty(@PathVariable long id) {
        return service.getStudentFaculty(id);
    }
    @GetMapping("/quantity-of-students")
    public ResponseEntity<Integer> getQualityOfStudents() {
        return ResponseEntity.ok(service.getQualityOfStudents());

    }
    @GetMapping("/average-age-of-students")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(service.getAverageAge());
    }
    @GetMapping("/the-last-five-students")
    public ResponseEntity<List<Student>> grtLastStudents() {
        return ResponseEntity.ok(service.getLastStudents());
    }

    @GetMapping("/students/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
        List<Student> students = service.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/names/{letter}")
    public ResponseEntity<List<String>> getNamesStartingWithA(@PathVariable String letter) {
        return ResponseEntity.ok(service.getNameStarWithA(letter));
    }
    @GetMapping("/studentage")
    public Double getAverageAgeByStreamApi() {
        return service.getAverageAgeByStreamApi();
    }
}
