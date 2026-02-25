import Model.*;
import Service.UniversityManager;
import Util.FileManager;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String STUDENTS_FILE = "students.txt";
    private static final String COURSES_FILE = "courses.txt";
    private static final String INSTRUCTORS_FILE = "instructors.txt";
    private static UniversityManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = new UniversityManager();
        scanner = new Scanner(System.in);

        loadData();
        showMenu();
    }

    private static void loadData() {
        try {
            manager.setStudents(FileManager.loadStudents(STUDENTS_FILE));
            manager.setCourses(FileManager.loadCourses(COURSES_FILE));
            manager.setInstructors(FileManager.loadInstructors(INSTRUCTORS_FILE));
            System.out.println("Data loaded successfully.\n");
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.\n");
        }
    }

    private static void showMenu() {
        while (true) {
            System.out.println("=== University Management System ===");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll in Course");
            System.out.println("4. View Student Record");
            System.out.println("5. Generate Dean's List");
            System.out.println("6. Register Instructor");
            System.out.println("7. Assign Instructor to Course");
            System.out.println("8. Calculate Student Tuition");
            System.out.println("9. View All Instructors");
            System.out.println("10. Save and Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: registerStudent();
                break;
                case 2: createCourse();
                break;
                case 3: enrollInCourse();
                break;
                case 4: viewStudentRecord();
                break;
                case 5: manager.generateDeansList();
                break;
                case 6: registerInstructor();
                break;
                case 7: assignInstructorToCourse();
                break;
                case 8: calculateTuition();
                break;
                case 9: viewAllInstructors();
                break;
                case 10: saveAndExit();
                return;
                default: System.out.println("Invalid option.\n");
            }
        }
    }

    private static void registerStudent() {
        System.out.print("Type (1=Undergraduate, 2=Graduate): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        System.out.print("GPA: ");
        double gpa = scanner.nextDouble();
        System.out.print("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = type == 2 
            ? new GraduateStudent(name, email, dept, gpa, id)
            : new UndergraduateStudent(name, email, dept, gpa, id);

        manager.registerStudent(student);
        System.out.println("Student registered successfully.\n");
    }

    private static void createCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();

        manager.createCourse(new Course(name, credits));
        System.out.println("Course created successfully.\n");
    }

    private static void enrollInCourse() {
        System.out.print("Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine();

        Student student = manager.getStudents().stream()
            .filter(s -> s.getStudentID() == studentId)
            .findFirst().orElse(null);

        Course course = manager.getCourses().stream()
            .filter(c -> c.getName().equalsIgnoreCase(courseName))
            .findFirst().orElse(null);

        if (student == null || course == null) {
            System.out.println("Student or Course not found.\n");
            return;
        }

        try {
            manager.enrollStudentInCourse(student, course, grade);
            System.out.println("Enrollment successful.\n");
        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void viewStudentRecord() {
        System.out.print("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = manager.getStudents().stream()
            .filter(s -> s.getStudentID() == id)
            .findFirst().orElse(null);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }

        student.displayInfo();
        System.out.println("Tuition: $" + student.calculateTuition());
        System.out.println("Courses:");
        student.getCourses().forEach((course, grade) ->
            System.out.println("  - " + course.getName() + ": " + grade));
        System.out.println();
    }

    private static void saveAndExit() {
        try {
            FileManager.saveStudents(STUDENTS_FILE, manager.getStudents());
            FileManager.saveCourses(COURSES_FILE, manager.getCourses());
            FileManager.saveInstructors(INSTRUCTORS_FILE, manager.getInstructors());
            System.out.println("Data saved successfully. Goodbye!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        scanner.close();
    }

    private static void registerInstructor() {
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        System.out.print("Instructor ID: ");
        String id = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Instructor instructor = new Instructor(name, email, dept, id, salary);
        manager.registerInstructor(instructor);
        System.out.println("Instructor registered successfully.\n");
    }

    private static void assignInstructorToCourse() {
        System.out.print("Instructor ID: ");
        String instructorId = scanner.nextLine();
        System.out.print("Course Name: ");
        String courseName = scanner.nextLine();

        Instructor instructor = manager.getInstructors().stream()
            .filter(i -> i.getID().equals(instructorId))
            .findFirst().orElse(null);

        Course course = manager.getCourses().stream()
            .filter(c -> c.getName().equalsIgnoreCase(courseName))
            .findFirst().orElse(null);

        if (instructor == null || course == null) {
            System.out.println("Instructor or Course not found.\n");
            return;
        }

        manager.assignInstructorToCourse(instructor, course);
        System.out.println("Instructor assigned successfully.\n");
    }

    private static void calculateTuition() {
        System.out.print("Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = manager.getStudents().stream()
            .filter(s -> s.getStudentID() == id)
            .findFirst().orElse(null);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }

        String type = student instanceof GraduateStudent ? "Graduate" : "Undergraduate";
        System.out.println("\n=== Tuition Details ===");
        System.out.println("Student: " + student.getFullNames());
        System.out.println("Type: " + type);
        System.out.println("Tuition Fee: $" + student.calculateTuition());
        System.out.println();
    }

    private static void viewAllInstructors() {
        if (manager.getInstructors().isEmpty()) {
            System.out.println("No instructors registered.\n");
            return;
        }
        System.out.println("\n=== All Instructors ===");
        manager.getInstructors().forEach(Instructor::displayInfo);
        System.out.println();
    }
}
