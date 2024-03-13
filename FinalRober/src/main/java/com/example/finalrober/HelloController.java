package com.example.finalrober;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ImageView splashImageView;

    public void initialize() {
        //Cerrar a los 3 segundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.show();

                // Cerrar la ventana actual
                Stage splashStage = (Stage) splashImageView.getScene().getWindow();
                splashStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        // Iniciar la línea de tiempo
        timeline.play();
    }
}
