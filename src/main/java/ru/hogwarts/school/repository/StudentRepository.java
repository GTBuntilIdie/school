package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentList;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteById(long id);
    Collection<Student> findAllByAge(int age);
    Collection<Student> findAllByFaculty_Id(long facultyId);

    Collection<Student> findAllByAgeBetween(int min, int max);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getTheNumberOfStudents();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double getAverageAge();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> lastStudents();
    List<Student> getStudentsByName(String name);
}
