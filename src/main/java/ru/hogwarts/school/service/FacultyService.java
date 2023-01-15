package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Calling method createFaculty");
        faculty.setId(null);
        return facultyRepository.save(faculty);

    }
    public Faculty read(long id) {
        logger.debug("Calling method read (facultyId = {})", id);
        return facultyRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));

    }

    public Faculty update(long id, Faculty faculty) {
        logger.debug("Calling method update (facultyId = {})", faculty.getId());
        Faculty oldFaculty = read(id);
        oldFaculty.setName(faculty.getName());
        oldFaculty.setColor(faculty.getColor());
        return facultyRepository.save(oldFaculty);
    }
    public Faculty deleteFaculty(long id) {
        logger.debug("Calling method deleteFaculty (facultyId = {})", id);
        Faculty faculty = read(id);
        facultyRepository.deleteById(id);
        return faculty;
    }
    public Collection<Faculty> findByColor(String color) {
        logger.debug("Calling method findByColor (color = {})", color);
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> findAllFacultiesByNameOrColor(String nameOrColor) {
        logger.debug("Calling method findFacultiesByNameOrColor (nameOrColor = {})", nameOrColor);
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(nameOrColor, nameOrColor);
    }
    public Collection<Student> getStudentsByFacultyId(long id) {
        logger.debug("Calling method getStudentsByFacultyId (facultyId = {})", id);
        Faculty faculty = read(id);
        return studentRepository.findAllByFaculty_Id(faculty.getId());
    }

}
