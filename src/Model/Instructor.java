package Model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {

private double Salary;
private String ID;
private List<Course> CoursesTeaching;

    public Instructor(String FullNames, String Email, String Department, String ID, double Salary) {
        super(FullNames, Email, Department);
        this.ID= ID;
        this.Salary = Salary;
        this.CoursesTeaching= new ArrayList<>();
    }

    public String getID() {

        return ID;
    }

    public double getSalary() {

        return Salary;
    }
    public List<Course>getCoursesTeaching(){

        return CoursesTeaching;
    }

    public void setSalary(double salary) {

        Salary = salary;
    }
    public void assignCourse(Course course){

        CoursesTeaching.add(course);
    }

    @Override
    public void displayInfo() {
        System.out.println("Instructor: " + getFullNames() +
                ", ID: " + getID() +
                ", Salary: $" + Salary +
                "Email:"+ getEmail());
    }
}
