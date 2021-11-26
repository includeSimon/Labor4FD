//package com.labor.Repository;
//
//import com.labor.Exceptions.NullException;
//import com.labor.Model.Course;
//import com.labor.Model.Student;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class StudentRepositoryTest {
//    private Student harryPotter;
//    private Student hermioneGranger;
//    private Student ronWeasly;
//
//    private StudentRepository studentRepo;
//
//    @BeforeEach
//    void addExamples(){
//        harryPotter = new Student(1,"Harry", "Potter");
//        hermioneGranger = new Student(2,"Hermione", "Granger");
//        ronWeasly = new Student(3,"Ron", "weasly");
//
//        List<Student> studentList = new ArrayList<>();
//
//        studentList.add(harryPotter);
//        studentList.add(hermioneGranger);
//        studentList.add(ronWeasly);
//
//        studentRepo = new StudentRepository(studentList);
//    }
//
//    @Test
//    void findOne() {
//        try {
//            assertEquals(harryPotter, studentRepo.findOne(1));
//        }catch(NullException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test
//    void save() {
//        try {
//            Student ginnyWeasley = new Student(4,"Ginny", "Weasley");
//            studentRepo.save(ginnyWeasley);
//
//            assertEquals(ginnyWeasley,studentRepo.findOne(4));
//        }catch(NullException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test
//    void update() {
//        try {
//            Student differentRon = studentRepo.findOne(3);
//            differentRon.setLastName("Weasly");
//
//            studentRepo.update(differentRon);
//
//            assertEquals(differentRon, studentRepo.findOne(3));
//        }catch(NullException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test
//    void delete() {
//        try {
//            studentRepo.delete(1);
//
//            assertNull(studentRepo.findOne(1));
//        }catch(NullException e){
//            System.out.println(e.getMessage());
//        }
//    }
//}