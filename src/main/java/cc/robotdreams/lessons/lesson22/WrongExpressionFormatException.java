package cc.robotdreams.lessons.lesson22;

public class WrongExpressionFormatException extends RuntimeException
{
    public WrongExpressionFormatException() {

    }

    public WrongExpressionFormatException(String message) {
        super("Expression has wrong format: " + message);
    }
}
