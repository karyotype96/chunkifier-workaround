/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chunkifier;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import java.io.File;
import fileio.*;

/**
 *
 * @author seanc
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private Button Browse;
    @FXML
    private Button SaveTo;
    @FXML
    private Button Chunkify;
    @FXML
    private Button ChunkifyAndSend;
    @FXML
    private Button ReceiveFiles;
    @FXML
    private Button Options;
    
    File toChunkify;
    File toSaveAt;
    String filename;
    
    FileChooser fchooser;
    DirectoryChooser dchooser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fchooser = new FileChooser();
        fchooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fchooser.setTitle("Find file to chunkify...");
        
        dchooser = new DirectoryChooser();
        dchooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        dchooser.setTitle("Save chunkified file to folder...");
        
    }    
    
    @FXML
    private void getFileLocation(){
        toChunkify = fchooser.showOpenDialog(TitleLabel.getScene().getWindow());
        if (toChunkify != null){
            fromField.setText(toChunkify.getAbsolutePath());
        }
    }
    
    @FXML
    private void getFileSaveLocation(){
        toSaveAt = dchooser.showDialog(TitleLabel.getScene().getWindow());
        if (toSaveAt != null){
            toField.setText(toSaveAt.getAbsolutePath());
        }
    }
    
    @FXML
    private void chunkify(){
        FileReader f = new FileReader();
        f.setStoreSize(1024);
        f.setNOB(toChunkify);
        try {
            f.createBuffers();
            f.createFiles(toSaveAt.getAbsolutePath(), filename = JOptionPane.showInputDialog("Name of your files: "));
            f.readFileToBuffers();
            f.writeToFiles();
            f.removeBuffers();
            AlertHandler.alertConf("Success", "File successfully chunkified.");
        } catch (StoreSizeZeroException | BuffersNullException e){
            System.out.println(e.getMessage());
            f.removeBuffers();
            AlertHandler.alertFail("Failed", e.getMessage());
        } catch (IOException e){
            f.removeBuffers();
            AlertHandler.alertFail("Failed", "File I/O Error");
        }
    }
    
}
