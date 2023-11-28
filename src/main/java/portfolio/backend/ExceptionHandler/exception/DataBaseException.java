package portfolio.backend.ExceptionHandler.exception;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String message){
        super(message);
    }
}
