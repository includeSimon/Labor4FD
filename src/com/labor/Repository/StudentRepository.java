package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Student;

import java.util.List;

public class StudentRepository extends InMemoryRepository<Student>{
    public StudentRepository(List<Student> studentList) {

        super(studentList);
    }


    @Override
    public Student findOne(Integer id) throws NullException {
        if (id == null) {
            throw new NullException("The student id is null");
        }

        for(Student Student : this.objectList)
        {
            if(Student.getId() == id)
                return Student;
        }

        return null;
    }


    @Override
    public Student save(Student obj) throws NullException{
        if(obj == null)
            throw new NullException("The Student save object is null");

        if (this.findOne(obj.getId()) != null)
            return obj;


        this.objectList.add(obj);
        return null;
    }


    @Override
    public Student update(Student obj) throws NullException{
        if(obj == null)
            throw new NullException("The Student update object is null");


        //check if objects exists and return it if it does
        Student Student = this.findOne(obj.getId());
        if (Student == null)
            return obj;

        //remove old object and insert the new one
        this.objectList.remove(Student);
        this.objectList.add(obj);
        return null;
    }


    @Override
    public Student delete(Integer id) throws NullException{
        if(id == null)
            throw new NullException("The Course delete id is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Student deletedStudentCopy  = this.findOne(id);
        this.objectList.remove(deletedStudentCopy);

        return deletedStudentCopy;
    }

}
