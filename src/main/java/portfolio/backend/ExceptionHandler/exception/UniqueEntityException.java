package portfolio.backend.ExceptionHandler.exception;

public class UniqueEntityException extends RuntimeException{
    public UniqueEntityException(String message){
        super(message);
    }
}