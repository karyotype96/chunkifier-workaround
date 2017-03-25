/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

/**
 *
 * @author seanc
 */
public class BuffersNullException extends Exception {
    public BuffersNullException() {
        super();
    }
    
    public BuffersNullException(String message){
        super(message);
    }
    
    public BuffersNullException(String message, Throwable cause){
        super(message, cause);
    }
    
    public BuffersNullException(Throwable cause){
        super(cause);
    }
}
