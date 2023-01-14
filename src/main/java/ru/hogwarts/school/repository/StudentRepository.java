package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;
import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteById(long id);
    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_Id(long facultyId);

}
