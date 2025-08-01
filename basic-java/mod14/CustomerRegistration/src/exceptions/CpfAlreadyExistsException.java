package exceptions;

public class CpfAlreadyExistsException extends Exception{
    public CpfAlreadyExistsException(String message){
        super(message);
    }
}
