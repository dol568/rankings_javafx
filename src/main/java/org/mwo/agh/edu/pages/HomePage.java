package org.mwo.agh.edu.pages;

import org.mwo.agh.edu.models.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    Data data = Data.getInstance();

    @FXML
    private Label labelHome;

    @FXML
    void uploadFiles(ActionEvent event) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            List<File> xlsFiles = data.listFiles(selectedDirectory.getAbsolutePath());
            if (!xlsFiles.isEmpty()) {
                data.setSpreadSheet(selectedDirectory.getAbsolutePath());
                labelHome.setText("Wczytano dane");
                labelHome.setTextFill(Paint.valueOf("GREEN"));
                labelHome.setLayoutX(418.0);
            } else {
                data.setSpreadSheet(null);
                labelHome.setText("Brak plików XLS");
                labelHome.setTextFill(Paint.valueOf("RED"));
                labelHome.setLayoutX(418.0);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (data.getSpreadSheet() != null) {
            labelHome.setText("Wczytano dane");
            labelHome.setTextFill(Paint.valueOf("GREEN"));
            labelHome.setLayoutX(418.0);
        }
    }
}