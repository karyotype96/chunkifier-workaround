/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import javafx.scene.control.Alert;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.io.RandomAccessFile;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author seanc
 */
public class FileReader {
    
    private int storeSize = 0;
    
    private int numberOfBuffers = 0;
    private String location;
    
    private RandomAccessFile raf;
    private LinkedList<ByteBuffer> bufs = new LinkedList<>();
    private LinkedList<File> chunks = new LinkedList<>();
    
    private FileChannel fc; //input channel
    private FileChannel oc; //output channel
    
    public void createBuffers(){
        ByteBuffer b;
        for (int i = 0; i < numberOfBuffers; i++){
            b = ByteBuffer.allocate(storeSize);
            bufs.add(b);
            b = null;
        }
    }
    
    public void setNOB(File file){
        numberOfBuffers = (int)Math.ceil(file.length() / storeSize) + 1;
        System.out.println("Number of buffers: " + numberOfBuffers);
        location = file.getAbsolutePath();
    }
    
    public void setStoreSize(int size){
        storeSize = size;
    }
    
    public void readFileToBuffers() throws StoreSizeZeroException,
            BuffersNullException, IOException {
        
        long bytesRead;
        FileChannel fc;
        
        raf = new RandomAccessFile(location, "rw");
        fc = raf.getChannel();
        
        if (bufs.isEmpty()){
            throw new BuffersNullException("Failed to initialize buffers");
        }
        
        if (storeSize <= 0){
            throw new StoreSizeZeroException("Buffer size is zero.");
        }
        
        for (int i = 0; i < numberOfBuffers; i++){
            bytesRead = fc.read(bufs.get(i));
        }
        
    }
    
    public void removeBuffers(){
        for (ByteBuffer b: bufs){
            b.clear();
        }
        
        bufs = null;
    }
    
    public void createFiles(String path, String filename) throws StoreSizeZeroException, BuffersNullException,
            IOException {
        
        if (bufs.isEmpty()){
            throw new BuffersNullException("Failed to initialize buffers");
        }
        
        if (storeSize <= 0){
            throw new StoreSizeZeroException("Buffer size is zero.");
        }
        
        for (int i = 0; i < numberOfBuffers; i++){
            chunks.add(new File(path + "\\" + filename + String.valueOf(i) + ".ch"));
        }
        
        for (File chunk: chunks){
            chunk.createNewFile();
        }
        
    }
    
    public void writeToFiles() throws BuffersNullException, StoreSizeZeroException,
            IOException {
        if (bufs.isEmpty()){
            throw new BuffersNullException("Failed to initialize buffers");
        }
        
        if (storeSize <= 0){
            throw new StoreSizeZeroException("Buffer size is zero.");
        }
        
        for (int i = 0; i < bufs.size(); i++){
            Files.write(chunks.get(i).toPath(), bufs.get(i).array(), StandardOpenOption.APPEND);
        }
        
    }
    
}
