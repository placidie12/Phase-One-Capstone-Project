package Model;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
     private String Department;
     private int StudentID;
     private double GPA;
     private Map<Course, Double> Courses;

    public Student(String FullNames, String Email, String Department, double GPA, int StudentID) {
        super(FullNames, Email, Department);
        this.Department = Department;
         setGPA(GPA);
        this.StudentID = StudentID;
        this.Courses = new HashMap<>();
    }


    public String getDepartment() {

         return Department;
    }

    public int getStudentID() {

        return StudentID;
    }

    public Map<Course, Double> getCourses() {

        return Courses;
    }
    public void setDepartment(String department) {

         Department = department;
    }

    public void setStudentID(int studentID) {

         StudentID = studentID;
    }

    public void setGPA(double GPA) {
        if(GPA<0.0 || GPA>4.0){
             throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.GPA = GPA;
    }

    public void setCourses(Map<Course, Double> courses) {

         Courses = courses;
    }


    public double calculateTuition() {

        return 0.0;
     }

     public void EnrollCourse(Course course,double grade){

        getCourses().put(course,grade);
     }

    public double calculateGPA() {
        if (getCourses().isEmpty())
            return 0.0;

        double totalGradePoints = 0.0;

        for (double grade : getCourses().values()) {

            double gpaValue;
            if (grade >= 90) gpaValue = 4.0;
            else if (grade >= 80) gpaValue = 3.0;
            else if (grade >= 70) gpaValue = 2.0;
            else if (grade >= 60) gpaValue = 1.0;
            else gpaValue = 0.0;

            totalGradePoints += gpaValue;
        }

        return totalGradePoints / getCourses().size();
    }


    @Override
    public void displayInfo() {
        System.out.println("Student: " + getFullNames() +
                ", Email: " + getEmail() +
                ", Department: " + getDepartment() +
                ", GPA: " + calculateGPA());
    }

    public  double getGpa() {
        return GPA;
    }
}
