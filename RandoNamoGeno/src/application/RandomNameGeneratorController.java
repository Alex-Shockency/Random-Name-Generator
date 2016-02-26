/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooserBuilder;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Alex&Lauren
 */
public class RandomNameGeneratorController implements Initializable {

    @FXML
    private Label randomFirst;
    @FXML
    private Label randomLast;
    @FXML
    private Label error;
    private ArrayList<String> namePool = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void generateRandom(ActionEvent event) throws InterruptedException {
        if (namePool.isEmpty()) {
            FadeTransition ft = new FadeTransition(Duration.millis(3000), error);
            error.setText("Error no name file has been loaded.");
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            //ft.setCycleCount(Timeline.INDEFINITE);
            ft.play();
        }
        Random rand = new Random();
        int myrand0 = rand.nextInt(namePool.size());
        int myrand1 = 0;
        randomFirst.setText(namePool.get(myrand0));
        while (true) {
            myrand1 = rand.nextInt(namePool.size());
            if (myrand0 != myrand1) {
                randomLast.setText(namePool.get(myrand1));
                break;
            }
        }
    }

    public void readFile(ActionEvent event) {
        //String currentDir = System.getProperty("user.dir") + File.separator;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(randomFirst.getScene().getWindow());
        try {
            Scanner s = new Scanner(file).useDelimiter("\\s+");
            while (s.hasNext()) {
                namePool.add(s.next());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RandomNameGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
