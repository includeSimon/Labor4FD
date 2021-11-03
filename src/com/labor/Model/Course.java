package com.labor.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    private String name;
    private Person teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;
    private UUID id;       //unique identifier used for comparison between objects of the same class 'Course'

    public Course(String name, Person teacher, int maxEnrolment, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrolment;
        this.studentsEnrolled= new ArrayList<>();
        this.credits = credits;
        this.id=UUID.randomUUID();     //initializing a random id for each object
    }

    public String getName() {return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrolment() {
        return maxEnrollment;
    }

    public void setMaxEnrolment(int maxEnrolment) {
        this.maxEnrollment = maxEnrolment;
    }
    //com.labor.Model.Student
    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course name = '" + name +
                "', teacher = " + teacher +
                ", maxEnrollment = " + maxEnrollment +
                ", credits = " + credits +
                ", studentsEnrolled=" + studentsEnrolled +
                ", id=" + id;
    }

}
