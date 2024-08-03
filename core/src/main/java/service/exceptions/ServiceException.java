package service.exceptions;

public class ServiceException extends RuntimeException{
    public ServiceException(String msg){
        super(msg);
    }
}
