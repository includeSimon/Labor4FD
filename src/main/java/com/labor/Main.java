package com.labor;


import com.labor.Exceptions.NullException;
import com.labor.Model.Course;
import com.labor.Repository.CourseRepository;
import com.labor.Repository.StudentRepository;
import com.labor.Repository.TeacherRepository;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NullException {
        TeacherRepository teacherRepo = new TeacherRepository("teacherIn.json");
        CourseRepository courseRepo = new CourseRepository("courseIn.json", teacherRepo);
        StudentRepository studentRepo = new StudentRepository("studentIn.json");
        teacherRepo.readFromFile();
        teacherRepo.writeToFile();
        courseRepo.readFromFile();
        courseRepo.writeToFile();
        studentRepo.readFromFile();
        studentRepo.writeToFile();

//        List<Course> sortedByName = courseRepo.findAll();
//
//        System.out.println(sortedByName);
//
//        sortedByName = courseRepo.sortByName();
//
//        System.out.println(sortedByName);

//        System.out.println(studentRepo.findAll());
//
//        System.out.println(studentRepo.sortById());

//        System.out.println(studentRepo.filterByName());

//        System.out.println(courseRepo.filterByEnrollmentPlaces());
    }
}
/*
    De facut:
    -terminat teacherRepo si studentRepo
    -de revazut relatia dintre curs si student
    -rescris controller
    -rescris testele
    -facut metode pentru cerintele algoritmice din tema
    -facut view
 */