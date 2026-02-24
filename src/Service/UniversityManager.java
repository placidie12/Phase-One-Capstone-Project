package Service;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityManager {
    private List<Student> students;
    private List<Instructor> instructors;
    private List<Course> courses;

    public UniversityManager() {
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }


    public void registerStudent(Student student) {
        students.add(student);
    }


    public void registerInstructor(Instructor instructor) {
        instructors.add(instructor);
    }


    public void createCourse(Course course) {
        courses.add(course);
    }


    public void enrollStudentInCourse(Student student, Course course, double grade) {
        if (!course.getEnrolledStudents().contains(student)) {
            course.addStudent(student);
            student.EnrollCourse(course, 97.0);
        }
    }


    public void assignInstructorToCourse(Instructor instructor, Course course) {
        if (!instructor.getCoursesTeaching().contains(course)) {
            instructor.assignCourse(course);
            course.assignInstructor(instructor);
        }
    }


    public List<Student> getStudents() {
        return students;
    }


    public List<Instructor> getInstructors() {
        return instructors;
    }


    public List<Course> getCourses() {
        return courses;
    }


    public void displayAllStudents() {

        for (Student student : students) {
            student.displayInfo();
            System.out.println("Courses Enrolled:");
            student.getCourses().forEach((course, grade) ->
                    System.out.println("- " + course.getName() + ": " + grade));
            System.out.println();
        }
    }


    public void displayAllCourses() {

        for (Course course : courses) {
            System.out.println("Course: " + course.getName() + " (" + course.getCredits() + " credits)");

            if (course.getInstructor() != null) {
                System.out.println("Instructor: " + course.getInstructor().getFullNames());
            } else {
                System.out.println("Instructor: None");
            }

            System.out.println("Enrolled Students:");
            if (course.getEnrolledStudents().isEmpty()) {
                System.out.println("- No student enrolled");
            } else {
                for (Student student : course.getEnrolledStudents()) {
                    System.out.println("- " + student.getFullNames());
                }
            }
            System.out.println();
        }
    }
    }
