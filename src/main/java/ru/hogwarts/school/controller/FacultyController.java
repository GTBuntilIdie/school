package ru.hogwarts.school.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import java.util.Collection;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
    @PutMapping
    public Faculty update(@PathVariable long id,
                          @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.read(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }
    @GetMapping
    public Collection<Faculty> facultiesByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }
    @GetMapping("findByColor")
    public Collection<Faculty> findBy(@RequestParam String colorOrName) {
        return facultyService.findAllFacultiesByNameOrColor(colorOrName);
    }
    @GetMapping("/{id}/students")
    public Collection<Student> getStudentsByFaculty(@PathVariable long id) {
        return facultyService.getStudentsByFacultyId(id);
    }
    @GetMapping("/longestNameFaculty")
    public String getTheLongestFacultyName() {
        return facultyService.getTheLongestFacultyName();
    }

}
