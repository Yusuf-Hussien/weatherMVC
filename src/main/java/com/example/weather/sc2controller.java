package com.example.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.ResourceBundle;

public class sc2controller implements Initializable {

    @FXML private Button backbtn;
    @FXML private Text cityTXT;
    @FXML private Text regionTXT;
    @FXML private Text tempTXT;
    @FXML private Text statusTXT;
    @FXML private AnchorPane pane;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;

    private static final String baseURL = "https://api.weatherapi.com/v1/current.json?key=e47bd2dc280d4e2fad4144306251402&q=";
    private static StringBuilder url = new StringBuilder();
    private JSONObject json = null;
    private boolean isInvalid=false;

    public void Back(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("sc1.fxml"));
        root = loader.load();
        sc1controller controllerOfSc1 = loader.getController();
        stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Phi Weather");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // backbtn.setFocusTraversable(false);
        pane.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ESCAPE)backbtn.fire();
        });
    }

    public void setURL(String cityName)
    {
        url.setLength(0);
        url.append(baseURL);
        url.append(cityName);
    }
    public String getURL(String cityName)
    {
        return new String(baseURL+cityName);
    }

    public static JSONObject toJSON(String link) throws IOException, InterruptedException {
        HttpClient client;
        HttpRequest request;
        HttpResponse<String> response=null;
    try {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        client.close();
    }catch (IOException ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Internet Connection!");
        alert.setContentText("Please Check Your internet Connection");
        alert.show();
    } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
    }
    if(response!=null)
    {
        if(response.statusCode()>299) return null;
        return new JSONObject(response.body());
    }
    return  null;
        /*assert response != null;
        if(response.statusCode()>299) return null;
        return new JSONObject(response.body());*/
    }

    public void setWeather()
    {
        weatherINFO Weather = weatherINFO.fromJSON(json);
        cityTXT.setText(Weather.city());
        regionTXT.setText(Weather.region());
        tempTXT.setText(Weather.temp());
        statusTXT.setText(Weather.status());
    }

    public boolean validate(String cityName) throws IOException, InterruptedException {
        String url = getURL(cityName);
        json = toJSON(url);
        if(json!=null)  return true;
        return false;
    }
    public void app(String cityName) throws IOException, InterruptedException {
        String url = getURL(cityName);
        json = toJSON(url);
        setWeather();
    }
}
