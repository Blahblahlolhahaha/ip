package sadstudent.exceptions;
public class SadStudentException extends RuntimeException {
    private String message;

    public SadStudentException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
