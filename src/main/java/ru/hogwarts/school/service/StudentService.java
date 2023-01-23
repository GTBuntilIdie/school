package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentList;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private int count = 0;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.debug("Calling method createStudent");
        student.setId(null);
        return studentRepository.save(student);
    }
    public Student read(long id) {
        logger.debug("Calling method read (id = {})", id);
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    public Student update(long id, Student student) {
        logger.debug("Calling method update (studentId = {})", student.getId());
        Student oldStudent = read(id);
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        return studentRepository.save(oldStudent);
    }
    public Student deleteStudent(long id) {
        logger.debug("Calling method deleteStudent (studentId = {})", id);
        Student student = read(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Collection<Student> findByAge(int age) {
        logger.debug("Calling method findByAge (age = {})", age);
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> allStudentsByAgeBetween(int min, int max) {
        logger.debug("Calling method allStudentsByAgeBetween (minAge = {}, maxAge = {})", min, max);
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Faculty getStudentFaculty(long id) {
        logger.debug("Calling method getStudentFaculty (studentId = {})", id);
        return read(id).getFaculty();
    }
    public Integer getQualityOfStudents() {
        logger.debug("Calling method getQualityOfStudents");
        return studentRepository.getTheNumberOfStudents();
    }
    public Double getAverageAge() {
        logger.debug("Calling method getAverageAge");
        return studentRepository.getAverageAge();
    }
    public List<Student> getLastStudents() {
        logger.debug("Calling method getLastStudents");
        return studentRepository.lastStudents();
    }

    public List<Student> getStudentsByName(String name) {
        logger.debug("Calling method getStudentsByName (studentName = {})", name);
        return studentRepository.getStudentsByName(name);
    }
    public List<String> getNameStarWithA(String letter) {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(user -> user.getName())
                .filter(s -> s.startsWith(letter))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

    }
    public Double getAverageAgeByStreamApi() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToDouble(student -> student.getAge())
                .average()
                .getAsDouble();
    }

    public void getThreadStudentNames() {
        List<Student> students = studentRepository.findAll();
        PrintNamesSync(students, 0);
        PrintNamesSync(students, 2);
        new Thread(() -> {
            PrintNamesSync(students, 2);
            PrintNamesSync(students, 3);
        }).start();
        new Thread(() -> {
            PrintNamesSync(students, 4);
            PrintNamesSync(students, 5);
        }).start();
    }
    public synchronized void PrintNamesSync(List<Student> students, int number) {
        String name = students.get(number).getName();
        count++;
        System.out.println("name = " + name + "count: " + count);
    }
}
