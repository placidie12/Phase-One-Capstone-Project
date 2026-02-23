package Model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int credits;
    private List<Student> enrolledStudents;

    public Course(String name, int credits){
        this.name= name;
        this.credits= credits;
        this.enrolledStudents= new ArrayList<>();
    }
    public void addStudent(Student student){
        if (!enrolledStudents.contains(student)){
            enrolledStudents.add(student);

        }
    }
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getName() {
        return name; }
    public int getCredits() {
        return credits; }
}

