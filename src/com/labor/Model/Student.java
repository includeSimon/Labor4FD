package com.labor.Model;

import java.util.List;
import java.util.UUID;

public class Student extends Person {
    private long studentId;
    private int totalCredits;
    private List<Course> enrolledCourses;
    private UUID id;       //unique identifier used for comparison between objects of the same class

    public Student(String firstName, String lastName, long studentId, int totalCredits, List<Course> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
        this.id = UUID.randomUUID();
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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
                "studentId=" + studentId +
                ", totalCredits=" + totalCredits +
                ", enrolledCourses=" + enrolledCourses;
    }

    /**
     *
     * @param Other class object to compare with
     * @return true if the objects match, else false
     */

    public boolean equals(Student other) {
        if(this.studentId ==  other.getStudentId())
            return true;
        return false;
    }
}
