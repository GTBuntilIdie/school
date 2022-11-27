package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editFaculty);
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Faculty findFaculty = facultyService.findFaculty(id);
        if (findFaculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findFaculty);
    }
    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }
    @GetMapping("/color/{color}")
    public List<Faculty> facultyStudentsByColor(@PathVariable String color) {
        return facultyService.getFaculties().values().stream()
                .filter(faculty -> faculty.getColor() == color)
                .collect(Collectors.toList());
    }

}
