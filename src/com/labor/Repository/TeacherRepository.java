package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Teacher;

import java.util.List;
import java.util.UUID;

public class TeacherRepository extends InMemoryRepository<Teacher>{
    public TeacherRepository(List<Teacher> repo) {

        super(repo);
    }


    @Override
    public Teacher findOne(UUID id) throws NullException {
        if(id == null)
            throw new NullException("id is null");

        for(Teacher Teacher : this.repo)
        {
            if(Teacher.getId() == id)
                return Teacher;
        }

        return null;
    }


    @Override
    public Teacher save(Teacher obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");


        if (this.findOne(obj.getId()) != null)
            return obj;


        this.repo.add(obj);
        return null;
    }


    @Override
    public Teacher update(Teacher obj) throws NullException {

        if(obj == null)
            throw new NullException("The object is null");

        //check if objects exists and return it if it does
        Teacher Teacher = this.findOne(obj.getId());
        if (Teacher == null)
            return obj;

        //remove old object and insert the new one
        this.repo.remove(Teacher);
        this.repo.add(obj);
        return null;
    }


    @Override
    public Teacher delete(UUID id) throws NullException {

        if(id == null)
            throw new NullException("The object is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Teacher deletedTeacherCopy  = this.findOne(id);
        this.repo.remove(deletedTeacherCopy);

        return deletedTeacherCopy;
    }
}
