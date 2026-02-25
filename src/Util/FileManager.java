package Util;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveStudents(String filename, Iterable<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Student s : students) {
            String type = s instanceof GraduateStudent ? "GRADUATE" : "UNDERGRADUATE";
            writer.write(type + "," + s.getStudentID() + "," + s.getFullNames() + "," + 
                        s.getEmail() + "," + s.getDepartment() + "," + s.getGpa());
            writer.newLine();
        }
        writer.close();
    }

    public static List<Student> loadStudents(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return students;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String name = parts[2];
                String email = parts[3];
                String dept = parts[4];
                double gpa = Double.parseDouble(parts[5]);

                if (type.equals("GRAD")) {
                    students.add(new GraduateStudent(name, email, dept, gpa, id));
                } else {
                    students.add(new UndergraduateStudent(name, email, dept, gpa, id));
                }
            }
        }
        reader.close();
        return students;
    }

    public static void saveCourses(String filename, Iterable<Course> courses) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Course c : courses) {
            writer.write(c.getName() + "," + c.getCredits());
            writer.newLine();
        }
        writer.close();
    }

    public static List<Course> loadCourses(String filename) throws IOException {
        List<Course> courses = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return courses;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                courses.add(new Course(parts[0], Integer.parseInt(parts[1])));
            }
        }
        reader.close();
        return courses;
    }

    public static void saveInstructors(String filename, Iterable<Instructor> instructors) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Instructor i : instructors) {
            writer.write(i.getID() + "," + i.getFullNames() + "," + i.getEmail() + "," + 
                        i.getDepartment() + "," + i.getSalary());
            writer.newLine();
        }
        writer.close();
    }

    public static List<Instructor> loadInstructors(String filename) throws IOException {
        List<Instructor> instructors = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return instructors;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                String dept = parts[3];
                double salary = Double.parseDouble(parts[4]);
                instructors.add(new Instructor(name, email, dept, id, salary));
            }
        }
        reader.close();
        return instructors;
    }
}