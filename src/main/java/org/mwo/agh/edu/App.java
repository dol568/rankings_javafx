package org.mwo.agh.edu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    double x, y;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getSceneX() - x);
            primaryStage.setY(event.getSceneY() - y);
        });

        primaryStage.setScene(new Scene(root, 1400, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}