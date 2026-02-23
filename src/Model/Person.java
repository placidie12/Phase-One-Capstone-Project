package Model;

abstract class Person {
    private String FullNames;
    private String Email;
    private String Department;


    Person(String FullNames, String Email, String Department) {
        this.FullNames = FullNames;
        this.Email = Email;
        this.Department = Department;
    }

    public String getFullNames() {
        return FullNames;
    }

    public String getEmail() {
        return Email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setFullNames(String fullNames) {
        FullNames = fullNames;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDepartment(String department) {
        Department = department;
    }


abstract void displayInfo();
}




