package Exception;

public class StudentAlreadyEnrolledException extends RuntimeException {
    public StudentAlreadyEnrolledException(String message) {

        super(message);
    }
}
