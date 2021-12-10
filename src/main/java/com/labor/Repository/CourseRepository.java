package com.labor.Repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.labor.Exceptions.NullException;
import com.labor.Exceptions.SizeException;
import com.labor.Model.Course;
import com.labor.Model.Student;
import com.labor.Model.Teacher;
import org.mortbay.util.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;


public class CourseRepository extends FileRepository<Course>{
    private TeacherRepository teacherRepo;
    private StudentRepository studentRepo;

    public CourseRepository(String fileIn, TeacherRepository teacherRepo, StudentRepository studentRepo) throws IOException{
        super(fileIn);
        this.teacherRepo = teacherRepo; //we need a teacher repo reference for creating a course
        this.studentRepo = studentRepo;
        this.elemList = new ArrayList<Course>();

        //creating output file "courseOut.txt" (functions as a repository)
        try {
            this.fileOut = new File("courseOut.json");
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
     * Trying to read from "courseIn" file
     *
     * @throws IOException if an error occured while reading from the file
     */
    public void readFromFile() throws IOException, NullException {
        Reader reader = new BufferedReader(new FileReader(fileIn));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        //empty elemList before adding new objects
        elemList.clear();

        for (JsonNode pm : parser) {
            //reading from courseIn.json
            int id = pm.path("id").asInt();
            String name = pm.path("name").asText();
            int teacherId = pm.path("teacherId").asInt();
            int maxEnrollment = pm.path("maxEnrollment").asInt();
            int credits = pm.path("credits").asInt();

            //getting teacher from teacher Repo
            Teacher teacher = teacherRepo.findOne(teacherId);

            //creating course
            Course course = new Course(id,name,teacher,maxEnrollment,credits);
            //storing information in local repository
            this.elemList.add(course);

            //adding course to teacher list of courses
            teacher.addCourse(course);
        }
    }

    public void readStudentCourses() throws IOException, NullException, SizeException {
        Reader reader = new BufferedReader(new FileReader("studentCourses.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser) {
            //reading from courseIn.json
            int studentId = pm.path("studentId").asInt();
            int courseId = pm.path("courseId").asInt();

            //retrieving course
            try {
                Course course = findOne(courseId);
                Student student = studentRepo.findOne(studentId);
                course.enrollStudent(student);
                student.addCourse(course);
            } catch (NullException e){//| SizeException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * appends course objects to the "courseOut.txt" file
     *
     * @param courseList the course list we want to write
     * @throws IOException if the file has not been opened successfully
     */
    public void writeToFile(List<Course> courseList) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, courseList);
        }  catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void writeToFile() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(fileOut, elemList);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds element by searching line-by-line in repository file
     *
     * @param id id of searched object
     * @return  course object if it exists or null otherwise
     * @throws NullException if id is of null value
     */
    @Override
    public Course findOne(Integer id) throws NullException {
        if(id == null)
            throw new NullException("The Teacher find one id is null");

        for (Course course : elemList)
            if (course.getId() == id)
                return course;

        return null;
    }

    /**
     * Saving a course by appending to the already existing file repository
     *
     * @param course object course to be appended
     * @return null if finished successfully or course object if it already exists in file repository
     * @throws NullException if the course parameter is of null value
     * @throws IOException if the file has not been opened successfully
     */
    @Override
    public Course save(Course course) throws NullException, IOException {
        if(course == null)
            throw new NullException("The course save object is null");

        if (this.findOne(course.getId()) != null)
            return course;

        elemList.add(course);
        return null;
    }

    /**
     * Updating by creating
     *
     * @param course course to be updated
     * @return course if it already exists, null otherwise
     * @throws NullException if course is null
     */
    @Override
    public Course update(Course course) throws NullException {
        if(course==null)
            throw new NullException("The course update object is null");

        //check if objects is the same
        if (findOne(course.getId()) == course)
            return course;

        //remove old object and add new one
        Course oldCourse = findOne(course.getId());
        elemList.remove(oldCourse);
        elemList.add(course);

        return null;
    }


    @Override
    public Course delete(Integer id) throws NullException  {
        if(id==null)
            throw new NullException("The course delete id is null");

        //object does not exist
        if (this.findOne(id) == null)
            return null;

        //removing object with the given id and return it
        Course deletedCourseCopy  = findOne(id);
        elemList.remove(deletedCourseCopy);

        return deletedCourseCopy;
    }

    @Override
    public List<Course> findAll(){
        return  elemList;
    }



    public List<Course> sortByName(){
        List<Course> courseList = elemList;

        Collections.sort(courseList, (elem1,elem2) -> elem1.getName().compareTo(elem2.getName()));

        return courseList;
    }

    /**
     * Filters course list so that every course has at least 100 enrollment places
     *
     * @return list of filtered courses
     */
    public List<Course> filterByEnrollmentPlaces() {
        return elemList.stream()
                .filter(c -> c.getMaxEnrollment() >= 100)
                .collect(Collectors.toList());
    }
}
