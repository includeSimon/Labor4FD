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



}
