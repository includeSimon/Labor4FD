package com.labor.Controller;

import com.labor.Exceptions.InputException;
import com.labor.Exceptions.NullException;
import com.labor.Exceptions.SizeException;
import com.labor.Model.Course;
import com.labor.Model.Student;
import com.labor.Model.Teacher;
import com.labor.Repository.CourseRepository;
import com.labor.Repository.StudentRepository;
import com.labor.Repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;
    private CourseRepository courseRepo;

    public RegistrationSystem(StudentRepository studentRepo, TeacherRepository teacherRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
    }

    /**
     * for each repository reads data from file
     */
    public void readDataFromFile(){

    }

    /**
     * Enroll a student to a course
     *
     * @param course   , Course object
     * @param student, Student object
     * @return true if successfully enrolled, else false
     * @throws NullException if course or student do not exist in repo,
     *                       if student credits exceeds maximum amount of 30 credits,
     *                       if course has no free places or if student is already enrolled
     */
    public boolean register(Course course, Student student) throws NullException, SizeException, InputException {
        try {
            //check if the course provided as a parameter exists in repository
            if (courseRepo.findOne(course.getId()) == null) {
                throw new NullException("The course " + course.getName() + " doesn't exist");
            }
        } catch (NullException e) {
            System.out.println(e.getMessage());
        }

        //check if the student provided as a parameter exists in repository
        try {
            if (studentRepo.findOne(student.getId()) == null) {
                throw new NullException("The student" + student.getFirstName()
                        + " " + student.getLastName() + " doesn't exist");
            }
        } catch (NullException e) {
            System.out.println(e.getMessage());
        }

        //check if course has free places
        List<Student> enrolledStudents = course.getStudentsEnrolled();    //all students enrolled in the course

        if (enrolledStudents.size() == course.getMaxEnrollment()) {
            throw new SizeException("Course" + course.getName() + " has no free places!");
        }

        //check if student is already enrolled
        try {
            for (Student stud : enrolledStudents) {
                if (stud.equals(student))
                    throw new InputException("Student " + student.getFirstName()
                            + " " + student.getLastName() + " is already enrolled");
            }
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

        //if student has over 30 credits after enrolling to this course
        int studentCredits = student.getTotalCredits() + course.getCredits();
        try {
            if (studentCredits > 30)
                throw new InputException("Student " + student.getFirstName()
                        + " " + student.getLastName() + " has exceeded the number of 30 credits");

        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

        //modify old enrolledStudents list
        enrolledStudents.add(student);

        //set the new list as the course's new enrolledStudent list
        course.setStudentsEnrolled(enrolledStudents);

        //update course repository so that we save the modification
        try {
            courseRepo.update(course);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //update total credits of student
        student.setTotalCredits(studentCredits);

        //add new course to student courses list
        List<Course> studentCourses = student.getEnrolledCourses();
        studentCourses.add(course);
        student.setEnrolledCourses(studentCourses);

        //update students Repo
        try {
            studentRepo.update(student);
        } catch (NullException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * Find all the courses with free spots
     *
     * @return courses with free places
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> courseList = new ArrayList<>();

        for (Course course : courseRepo.findAll()) {
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment())
                courseList.add(course);
        }
        return courseList;
    }

    /**
     * Retrieve all students enrolled to a course
     *
     * @param course Course object
     * @return list of students enrolled to the given course, or null if course is NULL
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course) {
        /* find course in the course repo */
        try {
            if (courseRepo.findOne(course.getId()) != null) {
                return course.getStudentsEnrolled();
            }
        } catch (NullException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Delete a course from a teacher. Removing course from the teacher's courses list, from the students' enrolled lists and from the course repo
     * Update number of credits of certain students
     *
     * @param teacher Teacher object from whom we delete a course
     * @param course  Course object, from the teacher's list, to be deleted
     * @return true if successfully deleted
     * @throws InputException if teacher or course do not exist in te repo lists,
     *                        or if the course does not correspond to that teacher
     *                        deleting course from the teacher's teaching list, from the students enrolled list and from the courses repo
     */

    public boolean deleteCourseFromTeacher(Teacher teacher, Course course) throws InputException {

        //check if course exists in repository
        try {
            if (courseRepo.findOne(course.getId()) == null) {
                throw new InputException("Course " + course.getName() + " doesn't exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //check if teacher exists in repository
        try {
            if (teacherRepo.findOne(teacher.getId()) == null) {
                throw new InputException("Teacher " + teacher.getFirstName()
                        + " " + teacher.getLastName() + " doesn't exist");
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }

        //check if course exists in teacher
        List<Course> teacherCourses = teacher.getCourses();
        if (!teacherCourses.contains(course))
            throw new InputException("Teacher " + teacher.getFirstName() + " "
                    + teacher.getLastName() + " has no course " + course.getName());


        // remove course from teacher
        teacherCourses.remove(course);
        teacher.setCourses(teacherCourses);

        //updating teacher in teacherRepo
        try {
            teacherRepo.update(teacher);
        } catch (Exception e3) {
            System.out.println(e3.getMessage());
        }

        //remove course from Course Repo (because a course corresponds to only 1 teacher)
        try {
            courseRepo.delete(course.getId());
        } catch (NullException e4) {
            System.out.println(e4.getMessage());
        }

        //delete course from all students enrolled
        List<Student> enrolledStudents = course.getStudentsEnrolled();

        for (Student student : enrolledStudents) {

            //for each enrolled student, get its courses list
            List<Course> studentCourses = student.getEnrolledCourses();

            //from that list remove course (method parameter)
            studentCourses.remove(course);

            //update student courses list
            student.setEnrolledCourses(studentCourses);

            //getTotalCredits returns the old credits
            student.setTotalCredits(student.getTotalCredits() - course.getCredits());

            //update in the Repo
            try {
                this.studentRepo.update(student);
            } catch (NullException e5) {
                System.out.println(e5.getMessage());
            }
        }

        return true;
    }

    /**
     * desc: Recalculate the sum of credits provided from the enrolled courses of the students
     * Update the credits sum for each student
     */
    public void updateStudentsCredits() {
        List<Student> stud = this.getAllStudents();

        for (Student student : stud) {
            List<Course> coursesEnrolled = student.getEnrolledCourses();
            int sum = 0;

            for (Course course : coursesEnrolled) {
                //calculate the total sum of credits for each student
                sum += course.getCredits();
            }

            //update the total sum of credits for the student
            student.setTotalCredits(sum);

            //update in the repo
            try {
                studentRepo.update(student);
            } catch (NullException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * desc: modifying credit number for a course, that leads to updating repo with the updated course and updating students' credits
     *
     * @param c Course object, which credits were updated
     */
    public void modifyCredits(Course c) {
        /* update course in the repo */
        try {
            this.courseRepo.update(c);
        } catch (NullException e) {
            System.out.println(e.getMessage());
        }

        /*update all students*/
        this.updateStudentsCredits();
    }


    /**
     * desc: get all students from the repo
     *
     * @return student list from the student repo
     */
    public List<Student> getAllStudents() {
        ArrayList<Student> allStudents = new ArrayList<>();
        for (Student stud : this.studentRepo.findAll()) {
            allStudents.add(stud);
        }
        return allStudents;
    }

    /**
     * desc: get all courses from the repo
     *
     * @return courses list from the course repo
     */
    public List<Course> getAllCourses() {
        ArrayList<Course> allCourses = new ArrayList<>();
        for (Course course : this.courseRepo.findAll()) {
            allCourses.add(course);
        }
        return allCourses;
    }

    /**
     * get all teachers from the repo
     *
     * @return teachers list from teh teacher repo
     */
    public List<Teacher> getAllTeachers() {
        ArrayList<Teacher> allTeachers = new ArrayList<>();

        for (Teacher teacher : this.teacherRepo.findAll()) {
            allTeachers.add(teacher);
        }
        return allTeachers;
    }


    /**
     * searching for a student in the repo by the id
     *
     * @param id of a Student object
     * @return Student object from the student repo list with the given id
     */
    public Student findOneStudent(Integer id) {
        try {
            return this.studentRepo.findOne(id);
        } catch (NullException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * desc: searching for a course in the repo by the id
     *
     * @param id of a Course object
     * @return Course object from the course repo list with the given id
     */
    public Course findOneCourse(Integer id) {
        try {
            return this.courseRepo.findOne(id);
        } catch (NullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * desc: searching for a teacher in the repo by the id
     *
     * @param id of a Teacher object
     * @return Teacher object from the teacher repo list with the given id
     */
    public Teacher findOneTeacher(Integer id) {
        try {
            return this.teacherRepo.findOne(id);
        } catch (NullException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
