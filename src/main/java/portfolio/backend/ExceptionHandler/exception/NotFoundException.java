package portfolio.backend.ExceptionHandler.exception;

import java.util.logging.Logger;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(Logger logger, String message){
        super(message);
        logger.warning(message);
    }
}