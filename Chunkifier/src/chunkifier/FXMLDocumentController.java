/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chunkifier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;

/**
 *
 * @author seanc
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField PathField;
    @FXML
    private Button Browse;
    @FXML
    private Button Chunkify;
    @FXML
    private Button ChunkifyAndSend;
    @FXML
    private Button ReceiveFiles;
    @FXML
    private Button Options;
    
    File toChunkify;
    FileChooser chooser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.setTitle("Find file to chunkify...");
        
    }    
    
    @FXML
    private void getFileLocation(){
        toChunkify = chooser.showOpenDialog(TitleLabel.getScene().getWindow());
        if (toChunkify != null){
            PathField.setText(toChunkify.getAbsolutePath());
        }
    }
    
}
