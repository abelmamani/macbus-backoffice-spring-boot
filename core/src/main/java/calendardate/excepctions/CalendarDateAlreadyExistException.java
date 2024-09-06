package calendardate.excepctions;

public class CalendarDateAlreadyExistException extends RuntimeException{
    public CalendarDateAlreadyExistException(String msg){
        super(msg);
    }
}
