package com.labor.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Course {
    private String name;
    private Teacher teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;
    private int id;

    public Course(int id, String name, Teacher teacher, int maxEnrollment, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled= new ArrayList<>();
        this.credits = credits;
        this.id=id;     //initializing a random id for each object
    }

    public String getName() {return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  " id= " + id+
                " courseName= " + name +
                " teacherId= " + teacher.getId() +
                " maxEnrollment= " + maxEnrollment +
                " credits= " + credits +
                " studentsEnrolled= " + studentsEnrolled;
    }

    /**
     * Method to check if two objects of type class have the same id
     * @param other the other object that is compared
     * @return true if objects are identical, false otherwise
     */
    public boolean equals(Course other) {
        if (this.id != other.getId()
            || this.getName() != other.getName()
            || this.getTeacher() != other.getTeacher()
            || this.getCredits() != other.getCredits()
            || this.getMaxEnrollment() != other.getMaxEnrollment()
            || this.getStudentsEnrolled() != other.getStudentsEnrolled())
                return false;

        return true;
    }

}
