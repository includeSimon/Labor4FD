package com.labor;


import com.labor.Exceptions.NullException;
import com.labor.Repository.CourseRepository;
import com.labor.Repository.StudentRepository;
import com.labor.Repository.TeacherRepository;

import java.io.IOException;

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