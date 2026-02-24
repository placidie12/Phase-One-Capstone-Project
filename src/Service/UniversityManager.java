package Service;

import Model.*;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.util.ArrayList;
import java.util.List;

public class UniversityManager {

    private List<Student> students;
    private List<Instructor> instructors;
    private List<Course> courses;

    // Constructor
    public UniversityManager() {
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }


    public void registerStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }


    public void registerInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }


    public void createCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }


    public void enrollStudentInCourse(Student student, Course course, double grade)
            throws CourseFullException, StudentAlreadyEnrolledException {


        if (course.isFull()) {
            throw new CourseFullException("Course " + course.getName() + " is full");
        }


        if (course.getEnrolledStudents().contains(student)) {
            throw new StudentAlreadyEnrolledException(
                    student.getFullNames() + " already enrolled in " + course.getName());
        }


        course.addStudent(student);
        student.EnrollCourse(course, grade); // Add course and grade to student
    }


    public void assignInstructorToCourse(Instructor instructor, Course course) {
        if (!instructor.getCoursesTeaching().contains(course)) {
            instructor.assignCourse(course);
            course.assignInstructor(instructor);
        }
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


    public double calculateAverageGpaByDepartment(String department) {
        return students.stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }

    public Student getTopStudent() {
        return students.stream()
                .max((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()))
                .orElse(null);
    }
}