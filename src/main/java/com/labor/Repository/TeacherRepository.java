package com.labor.Repository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.labor.Exceptions.NullException;
import com.labor.Model.Course;
import com.labor.Model.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;  // Import the IOException class to handle errors

public class TeacherRepository extends FileRepository<Teacher>{
    public TeacherRepository(String fileIn) throws IOException {
        super(fileIn);
        this.elemList = new ArrayList<Teacher>();

        //creating output file "teacherOut.json"
        try {
            this.fileOut = new File("teacherOut.json");
            if (fileOut.createNewFile()) {
                System.out.println("File created: " + fileOut.getName());
            } else {
                System.out.println("File " + fileOut + " exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Trying to read from "teacherIn.txt" file
     * @throws IOException if an error occurred while reading from the file
     */
    @Override
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

            //storing information in courseOut.txt
            elemList.add(new Teacher(id,firstName,lastName));
        }
    }

    @Override
    public void writeToFile(List<Teacher> teacherList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, teacherList);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, elemList);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     *
     */
    public Teacher findOne(Integer id) throws NullException{
        if(id == null)
            throw new NullException("The Teacher find one id is null");

       for (Teacher teacher : elemList)
           if (teacher.getId() == id)
               return teacher;

        return null;
    }


    @Override
    public Teacher save(Teacher teacher) throws NullException{
        if(teacher == null)
            throw new NullException("The Teacher save object is null");

        if (this.findOne(teacher.getId()) != null)
            return teacher;

        elemList.add(teacher);
        return null;
    }


    @Override
    public Teacher update(Teacher teacher) throws NullException{
        if(teacher == null)
            throw new NullException("The Teacher update object is null");

        //check if objects exists and return it if it does
        if (findOne(teacher.getId()) == teacher)
            return teacher;

        //remove old object and insert the new one
        Teacher oldTeacher = findOne(teacher.getId());
        elemList.remove(oldTeacher);
        elemList.add(teacher);

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
        elemList.remove(deletedTeacherCopy);

        return deletedTeacherCopy;
    }

    @Override
    public List<Teacher> findAll(){
        return elemList;
    }
}
