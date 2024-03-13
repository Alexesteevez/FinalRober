package com.example.finalrober;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Inicio {

    @FXML
    private Button btnCompletado;
    @FXML
    private Button btnMiCuenta;
    @FXML
    private Button btnVer;
    @FXML
    private Button btnSalir;
    @FXML
    private VBox mainVBox;
    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private ImageView imgInicio;
    @FXML
    private TableView<Tarea> tabla;

    private VerTodo verTodoController;

    @FXML
    private void initialize() {
        cargarContenidoPrincipal();
        btnVer.setOnAction(event -> cargarVerTodoFXML());

        // Establecer margen superior diferente para btnVer
        VBox.setMargin(btnMiCuenta, new javafx.geometry.Insets(285, 0, 0, 0));

        // Manejar eventos de ratón para cambiar el color de los botones
        btnCompletado.setOnMouseEntered(this::cambiarColorBoton);
        btnCompletado.setOnMouseExited(this::restaurarColorBoton);
        btnCompletado.setOnMouseClicked(this::fijarColorBoton);

        btnMiCuenta.setOnMouseEntered(this::cambiarColorBoton);
        btnMiCuenta.setOnMouseExited(this::restaurarColorBoton);
        btnMiCuenta.setOnMouseClicked(this::fijarColorBoton);

        btnVer.setOnMouseEntered(this::cambiarColorBoton);
        btnVer.setOnMouseExited(this::restaurarColorBoton);
        btnVer.setOnMouseClicked(this::fijarColorBoton);

        btnSalir.setOnMouseEntered(this::cambiarColorBoton);
        btnSalir.setOnMouseExited(this::restaurarColorBoton);
        btnSalir.setOnMouseClicked(this::fijarColorBoton);

        // Manejar el evento de clic en imgInicio
        imgInicio.setOnMouseClicked(this::handleImgInicioClick);
    }

    private void cargarContenidoPrincipal() {
        try {
            // Cargar el contenido principal desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contenidoPrincipal.fxml"));
            AnchorPane contenidoPrincipal = loader.load();

            // Establecer el contenido principal en el AnchorPane
            anchorPanePrincipal.getChildren().setAll(contenidoPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVerTodoFXML() {
        try {
            if (verTodoController == null) {
                // Cargar el archivo VerTodo.fxml solo si el controlador no ha sido inicializado
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VerTodo.fxml"));
                AnchorPane verTodoContent = loader.load();

                // Obtener el controlador de VerTodo
                verTodoController = loader.getController();

                // Cambiar el contenido del AnchorPane
                anchorPanePrincipal.getChildren().setAll(verTodoContent);
            } else {
                // Si el controlador ya ha sido inicializado, simplemente mostrar la vista existente
                anchorPanePrincipal.getChildren().setAll(verTodoController.getAnchorPanePrincipal());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cambiarColorBoton(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #2C2C2C;");
    }

    private void restaurarColorBoton(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #E6E6E6"); // Restaura el estilo predeterminado
    }

    private void fijarColorBoton(MouseEvent event) {
        // Establece el color fijo #2C2C2C
        ((Button) event.getSource()).setStyle("-fx-background-color: #2C2C2C;");
    }

    private void handleImgInicioClick(MouseEvent event) {
        cargarContenidoPrincipal();
    }
}
