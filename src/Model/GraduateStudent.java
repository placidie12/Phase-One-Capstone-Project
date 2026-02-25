package Model;

public class GraduateStudent extends Student {
    private int CreditRate= 3000;
    private double ResearchFee= 100000;

    public GraduateStudent(String FullNames, String Email, String Department,
                           double GPA, int StudentID) {
        super(FullNames, Email, Department, GPA, StudentID);
    }

    @Override
    public double calculateTuition() {
        int totalCredits = getCourses()
                .keySet()
                .stream()
                .mapToInt(Course::getCredits)
                .sum();

        return (totalCredits * CreditRate) + ResearchFee;
    }
}
