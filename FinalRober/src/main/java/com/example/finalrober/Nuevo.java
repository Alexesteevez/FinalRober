package com.example.finalrober;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Nuevo {

    @FXML
    private AnchorPane anchorPaneTareas;
    @FXML
    private Button btnAnadirTarea, btnSalir;
    @FXML
    private TableColumn<Tarea, String> colTareas; // Especifica el tipo de datos en TableColumn
    @FXML
    private Label lbCategoria;
    @FXML
    private Label lbEstado;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Tarea> tablaTareas; // Especifica el tipo de datos en TableView
    @FXML
    private TextField tfNombreTarea;
    private VerTodo verTodoController;
    private ObservableList<Tarea> tareas;

    public void setTareas(ObservableList<Tarea> tareas){
        this.tareas = tareas;
    }

    // Método para mostrar los detalles de la tarea seleccionada
    public void mostrarTarea(Tarea tarea) {
        lbTitulo.setText(tarea.getTitulo());
        lbCategoria.setText(tarea.getCategoria());
        lbFecha.setText(tarea.getFecha().toString()); // Suponiendo que la fecha es un LocalDate
        lbEstado.setText(tarea.getEstado());
    }

    @FXML
    void guardarNombreTarea(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event){
        try {
            if (verTodoController == null) {
                // Cargar el archivo VerTodo.fxml solo si el controlador no ha sido inicializado
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VerTodo.fxml"));
                AnchorPane verTodoContent = loader.load();

                // Obtener el controlador de VerTodo
                verTodoController = loader.getController();

                // Llamar al método para establecer los datos en la tabla y pasar la lista de tareas
                verTodoController.setTareas(tareas);

                // Cambiar el contenido del AnchorPane
                anchorPaneTareas.getChildren().setAll(verTodoContent);
            } else {
                // Si el controlador ya ha sido inicializado, simplemente mostrar la vista existente
                anchorPaneTareas.getChildren().setAll(verTodoController.getAnchorPanePrincipal());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
