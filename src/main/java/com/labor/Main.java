package com.labor;


import com.labor.Controller.RegistrationSystem;
import com.labor.Exceptions.InputException;
import com.labor.Exceptions.NullException;
import com.labor.Exceptions.SizeException;
import com.labor.Repository.CourseRepository;
import com.labor.Repository.StudentRepository;
import com.labor.Repository.TeacherRepository;
import com.labor.View.View;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NullException, SizeException, InputException {
        TeacherRepository teacherRepo = new TeacherRepository("teacherIn.json");
        StudentRepository studentRepo = new StudentRepository("studentIn.json");
        CourseRepository courseRepo = new CourseRepository("courseIn.json",teacherRepo,studentRepo);

        RegistrationSystem regSys = new RegistrationSystem(studentRepo,teacherRepo,courseRepo);
        View view = new View(regSys);

        view.run();

    }
}