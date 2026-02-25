package Service;

import Model.*;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;
import java.util.ArrayList;
import java.util.List;

public class UniversityManager {

    private List<Student> students;
    private List<Instructor> Instructors;
    private List<Course> courses;


    public UniversityManager() {
        this.students = new ArrayList<>();
        this.Instructors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }


    public void setStudents(List<Student> students) {

        this.students = students;
    }

    public void setCourses(List<Course> courses) {

        this.courses = courses;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.Instructors = instructors;
    }

    public void registerStudent(Student student) {

        students.add(student);
    }

    public List<Student> getStudents() {

        return students;
    }


    public void registerInstructor(Instructor instructor) {

        Instructors.add(instructor);
    }

    public List<Instructor> getInstructors() {

        return Instructors;
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
        student.EnrollCourse(course, grade);
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
    public void generateDeansList() {
        System.out.println("= Dean's List =");

        boolean found = students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .peek(s -> System.out.println(s.getFullNames() + " | GPA: " + s.getGpa()))
                .count() > 0;

        if (!found) {
            System.out.println("No students qualified for Dean's List.");
        }
    }
}
