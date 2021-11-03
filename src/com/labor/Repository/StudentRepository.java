package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Course;
import com.labor.Model.Student;

import java.util.List;
import java.util.UUID;

public class StudentRepository extends InMemoryRepository<Student>{
    public StudentRepository(List<Student> repo) {

        super(repo);
    }


    @Override
    public Student findOne(UUID id) throws NullException {
        if(id == null)
            throw new NullException("id is null");

        for(Student Student : this.repo)
        {
            if(Student.getId() == id)
                return Student;
        }

        return null;
    }


    @Override
    public Student save(Student obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");


        if (this.findOne(obj.getId()) != null)
            return obj;


        this.repo.add(obj);
        return null;
    }


    @Override
    public Student update(Student obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");

        //check if objects exists and return it if it does
        Student Student = this.findOne(obj.getId());
        if (Student == null)
            return obj;

        //remove old object and insert the new one
        this.repo.remove(Student);
        this.repo.add(obj);
        return null;
    }


    @Override
    public Student delete(UUID id) throws NullException {

        if(id == null)
            throw new NullException("The object is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Student deletedStudentCopy  = this.findOne(id);
        this.repo.remove(deletedStudentCopy);

        return deletedStudentCopy;
    }

}
