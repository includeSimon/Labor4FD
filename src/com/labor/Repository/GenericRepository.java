/*
voi pastra aceasta idee ca si proiect hobby
https://stackoverflow.com/questions/3437897/how-do-i-get-a-class-instance-of-generic-type-t
*/
//package com.labor.Repository;
//
//import com.labor.Exceptions.NullException;
//import com.labor.Model.T;
//
//import java.util.List;
//import java.util.UUID;
//
//public class GenericRepository<T> extends InMemoryRepository<T>{
//    public  GenericRepository(List<T> repo) {
//
//        super(repo);
//    }
//
//
//    @Override
//    public T findOne(UUID id) throws NullException {
//        if(id == null)
//            throw new NullException("id is null");
//
//        for(T element : this.repo)
//        {
//            if(element.getId() == id)
//                return T;
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public T save(T obj) throws NullException {
//
//        if(obj == null)
//            throw new NullException("The object is null");
//
//
//        if (this.findOne(obj.getId()) != null)
//            return obj;
//
//
//        this.repo.add(obj);
//        return null;
//    }
//
//
//    @Override
//    public T update(T obj) throws NullException {
//
//        if(obj == null)
//            throw new NullException("The object is null");
//
//        //check if objects exists and return it if it does
//        T T = this.findOne(obj.getId());
//        if (T == null)
//            return obj;
//
//        //remove old object and insert the new one
//        this.repo.remove(T);
//        this.repo.add(obj);
//        return null;
//    }
//
//
//    @Override
//    public T delete(UUID id) throws NullException {
//
//        if(id == null)
//            throw new NullException("The object is null");
//
//        //object does not exist
//        if (this.findOne(id) == null)
//            return null;
//
//        //removing object with the given id and return it
//        T deletedTCopy  = this.findOne(id);
//        this.repo.remove(deletedTCopy);
//
//        return deletedTCopy;
//    }
//}
