package com.labor;


import com.labor.Exceptions.NullException;
import com.labor.Repository.CourseRepository;
import com.labor.Repository.TeacherRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NullException {
            TeacherRepository teacherRepo = new TeacherRepository("teacherIn.json");
            CourseRepository courseRepo = new CourseRepository("courseIn.json", teacherRepo);
            teacherRepo.readFromFile();
            teacherRepo.writeToFile();
            courseRepo.readFromFile();
            courseRepo.writeToFile();

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