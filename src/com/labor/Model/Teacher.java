package com.labor.Model;

import java.util.List;
import java.util.UUID;

public class Teacher extends Person{
    public List<Course> courses;
    private UUID id;

    public Teacher(String firstName, String lastName, List<Course> courses) {
        super(firstName, lastName);
        this.courses = courses;
        this.id=UUID.randomUUID();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher " +
                "first name = " + firstName +
                " last name = " + lastName +
                "courses=" + courses +
                ", id=" + id +
                '}';
    }

    /**
     * Method to check if two objects of type class have the same id
     * @param other the other Object that is compared
     * @return true objects are identical, false otherwise
     */
    public boolean equals(Teacher other) {
        if(this.id ==  other.getId())
            return true;
        return false;
    }

}
