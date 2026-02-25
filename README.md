# Phase one Capstone Project

## University Management System

A comprehensive Java application demonstrating OOP principles, Collections, Exception Handling, and File Persistence.

## Features Implemented

### Lab 1: Object-Oriented Design ✅
- **Person** abstract class with **Student** and **Instructor** concrete classes
- Encapsulation with private fields (studentID, GPA, department)
- **Course** class with credits and roster management
- **calculateTuition()** with polymorphism:
  - UndergraduateStudent: Flat rate (50,000)
  - GraduateStudent: Per-credit rate (300) + Research fee (100,000)
- Collections:
  - Course: `List<Student>` for roster
  - Student: `Map<Course, Double>` for courses and grades

### Lab 2: Business Logic & Exception Handling ✅
- **UniversityManager** with:
  - `registerStudent()`
  - `createCourse()`
  - `enrollStudentInCourse()`
- Custom Exceptions:
  - `CourseFullException` (checked)
  - `StudentAlreadyEnrolledException` (unchecked)
- Java Streams:
  - Calculate average GPA by department
  - Find top-performing student
  - Generate Dean's List (GPA > 3.5)

### Lab 3: Persistence & Console Interface ✅
- **FileManager** with:
  - `saveStudents()` / `loadStudents()`
  - `saveCourses()` / `loadCourses()`
- **ConsoleApp** - Menu-driven interface:
  1. Register Student
  2. Create Course
  3. Enroll in Course
  4. View Student Record
  5. Generate Dean's List
  6. Save and Exit

## Project Structure
```
src/
├── Model/
│   ├── Person.java (abstract)
│   ├── Student.java
│   ├── UndergraduateStudent.java
│   ├── GraduateStudent.java
│   ├── Instructor.java
│   └── Course.java
├── Exception/
│   ├── CourseFullException.java
│   └── StudentAlreadyEnrolledException.java
├── Service/
│   └── UniversityManager.java
├── Util/
│   └── FileManager.java
├── Main.java (demo/testing)
└── ConsoleApp.java (interactive menu)
```

## How to Run

### Option 1: Demo Mode (Main.java)
```bash
javac src/**/*.java
java -cp src Main
```

### Option 2: Interactive Console (ConsoleApp.java)
```bash
javac src/**/*.java
java -cp src ConsoleApp
```

## Key Concepts Demonstrated

1. **Inheritance**: Person → Student → UndergraduateStudent/GraduateStudent
2. **Polymorphism**: calculateTuition() overridden in subclasses
3. **Encapsulation**: Private fields with getters/setters
4. **Collections**: List, Map, Set for managing relationships
5. **Exception Handling**: Custom checked and unchecked exceptions
6. **File I/O**: Persistent storage without database
7. **Streams API**: Functional programming for data processing

## Author
Mwizerwa - Phase One Capstone Project
