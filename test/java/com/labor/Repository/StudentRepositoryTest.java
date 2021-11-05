package com.labor.Repository;

import com.labor.Model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    private Student harryPotter;
    private Student hermioneGranger;
    private Student ronWeasly;

    private StudentRepository studentRepo;

    @BeforeEach
    void addExamples(){
        harryPotter = new Student(1,"Harry", "Potter");
        hermioneGranger = new Student(2,"Hermione", "Granger");
        ronWeasly = new Student(3,"Ron", "Weasly");

        List<Student> studentList = new ArrayList<>();

        studentList.add(harryPotter);
        studentList.add(hermioneGranger);
        studentList.add(ronWeasly);

        studentRepo = new StudentRepository(studentList);
    }

    @Test
    void findOne() {
        assertEquals(harryPotter, studentRepo.findOne(1));
    }

    @Test
    void save() {
        Student ginnyWeasley = new Student(4,"Ginny", "Weasley");
        studentRepo.save(ginnyWeasley);

        assertEquals(ginnyWeasley,studentRepo.findOne(4));
    }

    @Test
    void update() {
        Student olderGinny = studentRepo.findOne(4);
        olderGinny.setLastName("Potter");

        studentRepo.update(olderGinny);

        assertEquals(olderGinny, studentRepo.findOne(4));
    }

    @Test
    void delete() {
        studentRepo.delete(1);

        assertNull(studentRepo.findOne(1));
    }
}