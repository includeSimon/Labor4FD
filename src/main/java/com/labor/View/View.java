package com.labor.View;

import com.labor.Controller.RegistrationSystem;
import com.labor.Exceptions.InputException;
import com.labor.Exceptions.NullException;
import com.labor.Exceptions.SizeException;

import java.io.IOException;
import java.util.Scanner;

public class View {
    private RegistrationSystem regSys;


    public View(RegistrationSystem regSys){
        this.regSys = regSys;
    }

    public void welcomeMessage(){
        System.out.print("\n The program has started. Please select an option from below: \n");
    }

    public void showOptions(){
        System.out.print("1. read data form file\n");
        System.out.print("2. register student\n");
        System.out.print("3. retrieve courses with free places\n");
        System.out.print("4. retrieve students enrolled for a course\n");
        System.out.print("5. delete course from teacher\n");
        System.out.print("6. update student credits");
        System.out.print("7. modify course credits");
        System.out.print("8. get all students");
        System.out.print("9. get all courses");
        System.out.print("10. get all teachers");
        System.out.print("11. find one student");
        System.out.print("12. find one course");
        System.out.print("13. find one teacher");
    }

    public void registerStudent() throws InputException, NullException, SizeException {
        int courseId = 1, studentId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        courseId = scanner.nextInt();
        System.out.println("Enter student id: ");
        studentId = scanner.nextInt();

        regSys.register(regSys.findOneCourse(courseId),regSys.findOneStudent(studentId));
    }

    public void retrieveStudentsFromCourse(){
        int courseId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        courseId = scanner.nextInt();

        regSys.retrieveStudentsEnrolledForACourse(regSys.findOneCourse(courseId));
    }

    public void deleteCourseFromTeacher() throws InputException {
        int courseId = 1, teacherId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        courseId = scanner.nextInt();
        System.out.println("Enter teacher id: ");
        teacherId = scanner.nextInt();

        regSys.deleteCourseFromTeacher(regSys.findOneTeacher(teacherId),regSys.findOneCourse(courseId));
    }

    public void modifyCourseCredits(){
        int courseId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        courseId = scanner.nextInt();
        regSys.modifyCredits(regSys.findOneCourse(courseId));
    }

    public void findCourse(){
        int courseId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        courseId = scanner.nextInt();

        regSys.findOneCourse(courseId);
    }

    public void findStudent(){
        int studentId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        studentId = scanner.nextInt();

        regSys.findOneCourse(studentId);
    }

    public void findTeacher(){
        int teacherId = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter teacher id: ");
        teacherId = scanner.nextInt();

        regSys.findOneCourse(teacherId);
    }

    public void run() throws IOException, NullException, InputException, SizeException {
        int option = 0;
        welcomeMessage();

        do{
            showOptions();
            System.out.println("Enter option number: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();

            switch (option){
                case 1:
                    regSys.readDataFromFile();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    System.out.println(regSys.retrieveCoursesWithFreePlaces());
                    break;
                case 4:
                    retrieveStudentsFromCourse();
                    break;
                case 5:
                    deleteCourseFromTeacher();
                    break;
                case 6:
                    regSys.updateStudentCredits();
                    break;
                case 7:
                    modifyCourseCredits();
                    break;
                case 8:
                    System.out.println(regSys.getAllStudents());
                    break;
                case 9:
                    System.out.println(regSys.getAllCourses());
                    break;
                case 10:
                    System.out.println(regSys.getAllTeachers());
                    break;
                case 11:
                    findStudent();
                    break;
                case 12:
                    findCourse();
                    break;
                case 13:
                    findTeacher();
                    break;
                default:
                    option = 0;     //exit
            }
        }
        while (option != 0);
    }
}
