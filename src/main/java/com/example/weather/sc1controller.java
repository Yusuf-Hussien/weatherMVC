package com.example.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

//import java.awt.*;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class sc1controller implements Initializable {
    @FXML private TextField citytf;
    @FXML private Button searchbtn;
    @FXML private Hyperlink Hlink;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;


    private JSONObject json = null;
    private boolean isInvalid=false;

    public void Search(ActionEvent event) throws IOException, InterruptedException {

        //if valid
        loader = new FXMLLoader(getClass().getResource("sc2.fxml"));
        sc2controller  controller = loader.getController();
        if(!citytf.getText().trim().isEmpty() && !isInvalid && controller.validate(citytf.getText())) {

            root = loader.load();
            sc2controller controllerOfSc2 = loader.getController();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(citytf.getText());
            stage.show();
        }

        else{
            citytf.setText("Enter Valid City");
            citytf.setStyle("-fx-text-fill: red;");
            isInvalid=true;
            new Thread(()->{
                try {
                    Thread.sleep(2000);
                    citytf.clear();
                    citytf.setStyle("-fx-text-fill: black;");
                    isInvalid = false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHlink();
        citytf.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER )searchbtn.fire();
        });
    }



    private void setHlink()
    {
        Hlink.setOnAction(e->{
            try{
                //Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/yusuf-7ussien"));
                WebView webView =new WebView();
                webView.getEngine().load("https://www.linkedin.com/in/yusuf-7ussien");
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(webView));
                stage2.show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
