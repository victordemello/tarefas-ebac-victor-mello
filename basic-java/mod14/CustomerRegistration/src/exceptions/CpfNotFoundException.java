package exceptions;

public class CpfNotFoundException extends Exception{
    public CpfNotFoundException(String message){
        super(message);
    }
}
