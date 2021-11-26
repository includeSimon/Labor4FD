package com.labor.Repository;

import com.labor.Exceptions.NullException;
import com.labor.Model.Course;
import com.labor.Model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentRepository extends FileRepository<Student>{
    public StudentRepository(String fileIn) throws IOException {

        super(fileIn);

        //creating output file "studentOut.txt"
        try {
            this.fileOut = new File("studentOut.txt");
            if (fileOut.createNewFile()) {
                System.out.println("File created: " + fileOut.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Trying to read from "studentIn.txt" file
     * @throws IOException if an error occurred while reading from the file
     */
    public void readFromFile() throws IOException {
        try {
            File studentIn = fileIn;
            Scanner myReader = new Scanner(studentIn);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //@Override
   // public void writeToFile(Student object) throws IOException {

    //}

    /**
     * writes student objects to the "studentOut.txt" file
     * @param studentList the list of students we want to write
     * @throws IOException if the file has not been opened successfully
     */
    public void writeToFile(List<Student> studentList){
        try {
            FileWriter studentWriter = new FileWriter(fileOut);

            for (Student student : studentList)
                studentWriter.write(student.toString());

            studentWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public Student findOne(Integer id) throws NullException {
        if (id == null) {
            throw new NullException("The student id is null");
        }

        /*for(Student Student : this.repo)
        {
            if(Student.getId() == id)
                return Student;
        }*/

        return null;
    }


    @Override
    public Student save(Student obj) throws NullException{
        if(obj == null)
            throw new NullException("The Student save object is null");

        if (this.findOne(obj.getId()) != null)
            return obj;


        //this.repo.add(obj);
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
        //this.repo.remove(Student);
        //this.repo.add(obj);
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
        //this.repo.remove(deletedStudentCopy);

        return deletedStudentCopy;
    }

    @Override
    public List<Student> findAll(){
        return  elemList;
    }
}
