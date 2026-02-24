import Model.*;
import Service.UniversityManager;

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
                new Instructor("Dr Chris", "chris@gmail.com", "Information Technology", "I001", 1000000);




        manager.assignInstructorToCourse(instructor1, networking);
        manager.assignInstructorToCourse(instructor2, cybersecurity);
       manager.enrollStudentInCourse(student1, networking,91.7);
       manager.enrollStudentInCourse(student2, cybersecurity,97.8);

        student1.EnrollCourse(networking, 85.9);
        student2.EnrollCourse(cybersecurity,90.3);


//        manager.registerInstructor( Information System, JohnDoe);
//        manager.registerInstructor(Marketting,Amelia);


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
    }
}
