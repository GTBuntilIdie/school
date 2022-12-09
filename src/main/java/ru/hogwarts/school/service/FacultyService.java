package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    public Faculty findFaculty(long id) {
        if (facultyRepository.findById(id).isPresent()) {
            return facultyRepository.findById(id).get();
        }
        return null;
    }
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);
    }
    public Collection<Faculty> allFaculties() {
        return facultyRepository.findAll();
    }

}
