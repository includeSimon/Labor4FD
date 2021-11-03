package com.labor.Model;

import java.util.List;
import java.util.UUID;

public class Student extends Person {
    private int totalCredits;
    private List<Course> enrolledCourses;
    private UUID id;       //unique identifier used for comparison between objects of the same class

    public Student(String firstName, String lastName, int totalCredits, List<Course> enrolledCourses) {
        super(firstName, lastName);
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
        this.id = UUID.randomUUID();
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student " +
                "first name = " + firstName +
                " last name = " + lastName +
                "studentId=" + id +
                ", totalCredits=" + totalCredits +
                ", enrolledCourses=" + enrolledCourses;
    }

    /**
     * Method to check if two objects of type class have the same id
     * @param other the other object that is compared
     * @return true if the objects match, else false
     */
    public boolean equals(Student other) {
        if(this.id ==  other.getId())
            return true;
        return false;
    }
}
