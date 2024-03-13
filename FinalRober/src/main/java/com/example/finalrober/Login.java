package com.example.finalrober;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField tfContrasenha;
    @FXML
    private Button btnEntrar;

    @FXML
    public void initialize() {
        // Configurar el evento del botón
        btnEntrar.setOnAction(event -> abrirVentanaInicio());
    }

    private void abrirVentanaInicio() {
        try {
            // Obtener los datos del nombre y la contraseña
            String nombre = tfNombre.getText();
            String contrasenha = tfContrasenha.getText();

            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Parent root = loader.load();

            // Mostrar la nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) btnEntrar.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
