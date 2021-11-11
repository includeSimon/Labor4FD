package com.labor.Repository;
import com.labor.Exceptions.NullException;
import com.labor.Model.Teacher;
import java.util.List;

public class TeacherRepository extends InMemoryRepository<Teacher>{
    public TeacherRepository(List<Teacher> teacherList) {

        super(teacherList);
    }


    @Override
    public Teacher findOne(Integer id) throws NullException {
        if(id == null)
            throw new NullException("The Teacher find one id is null");

        for(Teacher Teacher : this.objectList)
        {
            if(Teacher.getId() == id)
                return Teacher;
        }

        return null;
    }


    @Override
    public Teacher save(Teacher obj) throws NullException{
        if(obj == null)
            throw new NullException("The Teacher save object is null");

        if (this.findOne(obj.getId()) != null)
            return obj;

        this.objectList.add(obj);
        return null;
    }


    @Override
    public Teacher update(Teacher obj) throws NullException{
        if(obj == null)
            throw new NullException("The Teacher update objectt is null");

        //check if objects exists and return it if it does
        Teacher Teacher = this.findOne(obj.getId());
        if (Teacher == null)
            return obj;

        //remove old object and insert the new one
        this.objectList.remove(Teacher);
        this.objectList.add(obj);
        return null;
    }


    @Override
    public Teacher delete(Integer id) throws NullException{
        if(id == null)
            throw new NullException("The Teacher delete id is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Teacher deletedTeacherCopy  = this.findOne(id);
        this.objectList.remove(deletedTeacherCopy);

        return deletedTeacherCopy;
    }
}
