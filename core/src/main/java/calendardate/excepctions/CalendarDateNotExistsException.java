package calendardate.excepctions;

public class CalendarDateNotExistsException extends RuntimeException{
    public CalendarDateNotExistsException(String msg){
        super(msg);
    }
}
