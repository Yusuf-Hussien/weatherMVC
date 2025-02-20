package com.example.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sc1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phi Weather");
        try {
            stage.getIcons().add(new Image("cloudy.png"));
        }catch (Exception ex){ex.printStackTrace();}
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}