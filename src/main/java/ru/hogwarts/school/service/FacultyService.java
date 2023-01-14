package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);

    }
    public Faculty read(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));

    }

    public Faculty update(long id, Faculty faculty) {
        Faculty oldFaculty = read(id);
        oldFaculty.setName(faculty.getName());
        oldFaculty.setColor(faculty.getColor());
        return facultyRepository.save(oldFaculty);
    }
    public Faculty deleteFaculty(long id) {
        Faculty faculty = read(id);
        facultyRepository.deleteById(id);
        return faculty;
    }
    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> findAllFacultiesByNameOrColor(String nameOrColor) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(nameOrColor, nameOrColor);
    }
    public Collection<Student> getStudentsByFaculty(long id) {
        Faculty faculty = read(id);
        return studentRepository.findAllByFaculty_Id(faculty.getId());
    }

}
