package exceptions;

public class AVLException extends Exception{
    public AVLException(){
        //Default constructor
    }

    public AVLException(String message){
        super(message);
    }
}
