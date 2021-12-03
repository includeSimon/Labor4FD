package com.labor.Repository;
import com.labor.Controller.RegistrationSystem;
import com.labor.Exceptions.InputException;
import com.labor.Exceptions.NullException;
import com.labor.Exceptions.SizeException;
import com.labor.Model.Course;
import com.labor.Model.Student;
import com.labor.Model.Teacher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ControllerTest {
    TeacherRepository teacherRepo = new TeacherRepository("teacherIn.json");
    StudentRepository studentRepo = new StudentRepository("studentIn.json");
    CourseRepository courseRepo = new CourseRepository("courseIn.json", teacherRepo, studentRepo);

    RegistrationSystem regSys = new RegistrationSystem(studentRepo,teacherRepo,courseRepo);

    public ControllerTest() throws IOException {
    }

    @Test
    void readFromData() throws IOException, NullException {
        regSys.readDataFromFile();

        assertEquals(3, teacherRepo.findAll().size());
        assertEquals(3, studentRepo.findAll().size());
        assertEquals(3, courseRepo.findAll().size());
    }


    @Test
    public void register() throws NullException, InputException, SizeException {
        //add ron weasly to flying course which has 5 credits
        regSys.register(courseRepo.findOne(1), studentRepo.findOne(1));

        assertEquals(1, courseRepo.findOne(1).getStudentsEnrolled().size());
        assertEquals(5, studentRepo.findOne(1).getTotalCredits());
    }

    @Test
    public void availableCourses() throws InputException, NullException, SizeException {
        List<Course> courses = courseRepo.findAll();

        //populating all courses besides the one with id 3
        for (Course course : courses)
            if (course.getId() == 1) { //100 students max
                for (int i = 1; i <= 99; i++)
                    regSys.register(course, new Student(i, "clone", "clone"));
            }
            else if (course.getId() == 2){
                for (int i = 1; i <= 60; i++)
                    regSys.register(course, new Student(i, "clone", "clone"));
            }

       assertEquals(1,regSys.retrieveCoursesWithFreePlaces().size());
    }

    @Test
    public void deletedCourseFromTeacher() throws NullException, InputException, IOException, SizeException {
        regSys.readDataFromFile();
        //enroll ron weasly to flying course
        regSys.register(courseRepo.findOne(1),studentRepo.findOne(1));

        //delete the course
        regSys.deleteCourseFromTeacher(teacherRepo.findOne(1),courseRepo.findOne(1));

        //now ron weasly is not enrolled in any course
        assertEquals(0,regSys.findOneStudent(1).getEnrolledCourses().size());
    }

}
