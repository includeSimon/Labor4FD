package com.labor.Repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.labor.Exceptions.NullException;
import com.labor.Model.Course;
import com.labor.Model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository extends FileRepository<Student>{
    public StudentRepository(String fileIn) throws IOException {
        super(fileIn);
        this.elemList = new ArrayList<Student>();

        //creating output file "studentOut.txt"
        try {
            this.fileOut = new File("studentOut.json");
            if (fileOut.createNewFile()) {
                System.out.println("File created: " + fileOut.getName());
            } else {
                System.out.println("File " + fileOut + "already exists");
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
        Reader reader = new BufferedReader(new FileReader(fileIn));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        //empty elemList before adding new objects
        elemList.clear();

        for (JsonNode pm : parser) {
            //reading from courseIn.json
            int id = pm.path("id").asInt();
            String firstName = pm.path("firstName").asText();
            String lastName = pm.path("lastName").asText();

            //storing information in local repository
            this.elemList.add(new Student(id,firstName,lastName));
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
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, studentList);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, elemList);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findOne(Integer id) throws NullException {
        if (id == null) {
            throw new NullException("The student id is null");
        }

        for(Student Student : elemList)
            if(Student.getId() == id)
                return Student;

        return null;
    }


    @Override
    public Student save(Student student) throws NullException{
        if(student == null)
            throw new NullException("The Student save object is null");

        if (this.findOne(student.getId()) != null)
            return student;


        elemList.add(student);
        return null;
    }


    @Override
    public Student update(Student student) throws NullException{
        if(student == null)
            throw new NullException("The Student update object is null");


        //check if objects exists and return it if it does
        if (findOne(student.getId()) == student)
            return student;

        //remove old object and insert the new one
        Student oldStudent = findOne(student.getId());
        elemList.remove(oldStudent);
        elemList.add(student);
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
        Student deletedStudentCopy  = findOne(id);
        elemList.remove(deletedStudentCopy);

        return deletedStudentCopy;
    }

    @Override
    public List<Student> findAll(){
        return  elemList;
    }

    /**
     * Sorts Student objects by id ascending
     * @return studentList list of sorted students
     */
    public List<Student> sortById(){
        List<Student> studentList = elemList;

        Collections.sort(studentList, (stud1,stud2) -> Integer.compare(stud1.getId(), stud2.getId()));

        return studentList;
    }

    /**
     * Filters a list of Students so that every student first name starts with letter 'H'
     * @return filtered student list
     */
    public List<Student> filterByName() {
        return elemList.stream()
                .filter(c -> c.getFirstName().startsWith("H"))
                .collect(Collectors.toList());
    }
}

