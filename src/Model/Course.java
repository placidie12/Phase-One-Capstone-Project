package Model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int credits;
    private Instructor Instructor;
    private List<Student> EnrolledStudents;

    public Course(String name, int credits){
        this.name= name;
        this.credits= credits;
        this.EnrolledStudents= new ArrayList<>();
    }
    public void addStudent(Student student){
        if (!EnrolledStudents.contains(student)){
            EnrolledStudents.add(student);
            System.out.println(student.getFullNames() + " added to " + this.getName());
        }
    }
    public void  assignInstructor (Instructor instructor) {

        this.Instructor= instructor;
    }
    public Instructor getInstructor() {

        return Instructor;
    }
    public List<Student> getEnrolledStudents() {

        return EnrolledStudents;
    }

    public String getName() {
        return name; }
    public int getCredits() {
        return credits; }

    public boolean isFull() {

        return false;
    }
}

