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
public class StoreSizeZeroException extends Exception {
    public StoreSizeZeroException() {
        super();
    }
    
    public StoreSizeZeroException(String message){
        super(message);
    }
    
    public StoreSizeZeroException(String message, Throwable cause){
        super(message, cause);
    }
    
    public StoreSizeZeroException(Throwable cause){
        super(cause);
    }
}
