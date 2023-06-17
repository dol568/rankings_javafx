package org.mwo.agh.edu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private Label exit;

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(e -> {
            System.exit(0);
        });

        try {
            loadFXML("home.fxml");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void home(ActionEvent actionEvent) throws IOException {
        loadFXML("home.fxml");
    }
    public void employeesToHours(ActionEvent actionEvent) throws IOException {
        loadFXML("employees.fxml");
    }
    public void monthsToHours(ActionEvent actionEvent) throws IOException {
        loadFXML("months.fxml");

    }
    public void daysToHours(ActionEvent actionEvent) throws IOException {
        loadFXML("days.fxml");

    }

    private void loadFXML(String fxmlPath) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource(fxmlPath));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}