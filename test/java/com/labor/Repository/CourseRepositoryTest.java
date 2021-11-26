    //package com.labor.Repository;
    //
    //import com.labor.Exceptions.NullException;
    //import com.labor.Model.Course;
    //import com.labor.Model.Teacher;
    //import org.junit.jupiter.api.BeforeEach;
    //import org.testng.annotations.Test;
    //
    //import java.util.ArrayList;
    //import java.util.List;
    //
    //import static org.junit.jupiter.api.Assertions.*;
    //
    //class CourseRepositoryTest {
    //    private Teacher flyingTeacher;
    //    private Teacher potionsTeacher;
    //    private Teacher transfigurationTeacher;
    //
    //    private Course flying;
    //    private Course potions;
    //    private Course transfiguration;
    //
    //    private CourseRepository courseRepo;
    //
    //    //we will need some examples to work with before each method call (each course requires a teacher)
    //    @BeforeEach
    //    void addExamples(){
    //
    //        //creating original examples
    //         flyingTeacher = new Teacher(1,"Rolanda","Hooch");
    //         potionsTeacher = new Teacher(2,"Severus","Snape");
    //         transfigurationTeacher = new Teacher(3,"Albus", "Dumbledore");
    //
    //         flying = new Course(1,"Flying", flyingTeacher, 100,5);
    //         potions = new Course(2,"Potions", potionsTeacher, 60, 6);
    //         transfiguration = new Course(3,"Transfiguration", transfigurationTeacher, 200, 10);
    //
    //        //creating a course list
    //        List<Course> courseList = new ArrayList<>();
    //
    //        //populating list
    //        courseList.add(flying);
    //        courseList.add(potions);
    //        courseList.add(transfiguration);
    //
    //        //updating repository with courses list
    //        courseRepo = new CourseRepository(courseList);
    //    }
    //
    //    @Test
    //    void findOne() {
    //        //searching for course "potions"
    //        try {
    //            Course foundCourse = courseRepo.findOne(2);
    //
    //            assertEquals(potions,foundCourse);
    //        }catch(NullException e){
    //            System.out.println(e.getMessage());
    //        }
    //    }
    //
    //    @Test
    //    void save() {
    //        Course defenseAgainstDarkArts = new Course(4, "Defense against the Dark Arts",
    //                potionsTeacher,10,10);
    //
    //        try {
    //            //saving new course
    //            courseRepo.save(defenseAgainstDarkArts);
    //
    //            assertEquals(defenseAgainstDarkArts, courseRepo.findOne(4));
    //        }catch(NullException e){
    //            System.out.println(e.getMessage());
    //        }
    //    }
    //
    //    @Test
    //    void update() {
    //
    //        try {
    //            //obtaining a course
    //            Course newCourse = courseRepo.findOne(1);
    //
    //            //modifying the course so that it is different from before
    //            newCourse.setMaxEnrollment(150);
    //
    //            //updating old course
    //            courseRepo.update(newCourse);
    //
    //            //making sure that the old course has been changed
    //            assertNotEquals(newCourse, courseRepo.findOne(1));
    //        }catch(NullException e){
    //            System.out.println(e.getMessage());
    //        }
    //    }
    //
    //    @Test
    //    void delete() {
    //        try {
    //            //obtining a course
    //            Course originalCourse = courseRepo.findOne(1);
    //
    //            //Dumbledor has been killed (deleting course)
    //            courseRepo.delete(1);
    //
    //            //trying to retrieve it again
    //            Course deletedCourse = courseRepo.findOne(1);
    //
    //            assertNull(deletedCourse);
    //        }catch(NullException e){
    //            System.out.println(e.getMessage());
    //        }
    //    }
    //}