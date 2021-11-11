package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {
    private Teacher flyingTeacher;
    private Teacher potionsTeacher;
    private Teacher transfigurationTeacher;

    private TeacherRepository teacherRepo;

    @BeforeEach
    void addExample(){
        flyingTeacher = new Teacher(1,"Rolanda","Hooch");
        potionsTeacher = new Teacher(2,"Severus","Snape");
        transfigurationTeacher = new Teacher(3,"Albus", "Dumbledore");

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(flyingTeacher);
        teacherList.add(potionsTeacher);

        teacherRepo = new TeacherRepository(teacherList);
    }

    @Test
    void findOne() {
        try {
            assertEquals(flyingTeacher, teacherRepo.findOne(1));
        }catch(NullException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void save() {
        try {
            teacherRepo.save(transfigurationTeacher);
            assertEquals(transfigurationTeacher, teacherRepo.findOne(3));
        }catch(NullException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
        try {
            Teacher oldTeacher = teacherRepo.findOne(1);
            oldTeacher.setLastName("Preda");
            teacherRepo.update(oldTeacher);

            assertEquals(oldTeacher,teacherRepo.findOne(1));
        }catch(NullException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void delete() {
        try {
            teacherRepo.delete(1);
            assertNull(teacherRepo.findOne(1));
        }catch(NullException e){
            System.out.println(e.getMessage());
        }
    }
}