package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Course;

import java.util.List;
import java.util.UUID;

public class CourseRepository extends InMemoryRepository<Course>{
    public CourseRepository(List<Course> repo) {

        super(repo);
    }


    @Override
    public Course findOne(UUID id) throws NullException {
        if(id == null)
            throw new NullException("id is Null");

        for(Course course : this.repo)
        {
            if(course.getId() == id)
                return course;
        }

        return null;
    }


    @Override
    public Course save(Course obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");


        if (this.findOne(obj.getId()) != null)
            return obj;


        this.repo.add(obj);
        return null;
    }


    @Override
    public Course update(Course obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");

        //check if objects exists and return it if it does
        Course course = this.findOne(obj.getId());
        if (course == null)
            return obj;

        //remove old object and insert the new one
        this.repo.remove(course);
        this.repo.add(obj);
        return null;
    }


    @Override
    public Course delete(UUID id) throws NullException {

        if(id == null)
            throw new NullException("The object is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Course deletedCourseCopy  = this.findOne(id);
        this.repo.remove(deletedCourseCopy);

        return deletedCourseCopy;
    }
}
