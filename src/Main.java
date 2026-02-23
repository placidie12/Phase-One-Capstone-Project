import Model.*;

public class Main {
    public static void main(String[] args) {


        Course Networking = new Course("Networking", 3);
        Course Cybersecurity = new Course("Cybersecurity", 10);


        UndergraduateStudent student1 =
                new UndergraduateStudent("ketty Pelly", "ketty@gmail.com", "Software Eng", 3.5, 12345);

        GraduateStudent student2 =
                new GraduateStudent("Alice sandy", "alice@gmail.com", "IT", 3.8, 123);


        Instructor instructor =
                new Instructor("Dr Brown", "brown@gmail.com", "Computer Science", "I001", 500000);


        instructor.assignCourse(Networking);


        student1.EnrollCourse(Networking, 85);
        student2.EnrollCourse(Cybersecurity, 90);

        Networking.addStudent(student1);
        Cybersecurity.addStudent(student2);


        System.out.println("Undergraduate Tuition: " + student1.calculateTuition());
        student1.displayInfo();

        System.out.println("\nGraduate Tuition: " + student2.calculateTuition());
        student2.displayInfo();

        System.out.println("\nInstructor Info:");
        instructor.displayInfo();
    }
}