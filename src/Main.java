import Model.*;
import Service.UniversityManager;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

public class Main {
    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();

        Course networking = new Course("Networking", 20);
        Course cybersecurity = new Course("Cybersecurity", 10);
        manager.createCourse(networking);
        manager.createCourse(cybersecurity);

        UndergraduateStudent student1 =
                new UndergraduateStudent("Ketty Pelly", "ketty@gmail.com", "Software Eng", 3.5, 12345);
        GraduateStudent student2 =
                new GraduateStudent("Alice Sandy", "alice@gmail.com", "IT", 3.8, 123);
        manager.registerStudent(student1);
        manager.registerStudent(student2);

        Instructor instructor1 =
                new Instructor("Dr Brown", "brown@gmail.com", "Computer Science", "I001", 500000);
        Instructor instructor2 =
                new Instructor("Dr Chris", "chris@gmail.com", "Information Technology", "I002", 1000000);
        manager.registerInstructor(instructor1);
        manager.registerInstructor(instructor2);


        manager.assignInstructorToCourse(instructor1, networking);
        manager.assignInstructorToCourse(instructor2, cybersecurity);


        try {
            manager.enrollStudentInCourse(student1, networking, 81.7);
        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }

        try {
            manager.enrollStudentInCourse(student2, cybersecurity, 97.8);
        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }

        System.out.println("= Student Information =");
        System.out.println("Undergraduate Tuition: " + student1.calculateTuition());
        student1.displayInfo();
        System.out.println("\nGraduate Tuition: " + student2.calculateTuition());
        student2.displayInfo();

        System.out.println("\n= Instructor Information =");
        instructor1.displayInfo();
        instructor2.displayInfo();

        System.out.println("\n= All Students =");
        manager.displayAllStudents();

        System.out.println("\n= All Courses =");
        manager.displayAllCourses();


        String dept1 = "Software Eng";
        double avgGpaDept1 = manager.calculateAverageGpaByDepartment(dept1);
        System.out.println("\nAverage GPA (" + dept1 + "): " + avgGpaDept1);

        String dept2 = "IT";
        double avgGpaDept2 = manager.calculateAverageGpaByDepartment(dept2);
        System.out.println("Average GPA (" + dept2 + "): " + avgGpaDept2);


        Student top = manager.getTopStudent();
        if (top != null) {
            System.out.println("\nTop Student: " + top.getFullNames() + " (GPA: " + top.getGpa() + ")");
        }
    }
}