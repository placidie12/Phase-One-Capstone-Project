package Model;

public class UndergraduateStudent extends Student {
    private  double GPA=4.0;
    double FlatRate= 50000;

    public UndergraduateStudent(String FullNames, String Email, String Department,double GPA,int StudentID) {
        super(FullNames, Email, Department,GPA,StudentID);
    }
    @Override
    public double calculateTuition() {

        return FlatRate;
    }
}

