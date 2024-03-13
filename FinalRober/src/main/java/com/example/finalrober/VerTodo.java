package com.example.finalrober;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VerTodo implements Initializable {

    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private Button btnAnadir, btnBorrar, btnEditar, btnAbrir;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Tarea> tabla;
    @FXML
    private TextField tfCategoria, tfTitulo;
    @FXML
    private TableColumn colID, colTitulo, colCategoria, colEstado, colFecha;
    @FXML
    private ComboBox<String> comboEstado;
    private ObservableList<Tarea> tareas;

    private int id = 1;

    public void setTareas(ObservableList<Tarea> tareas) {
        this.tareas = tareas;
        tabla.setItems(tareas);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Inicializar el ComboBox con los valores posibles del estado
        comboEstado.setItems(FXCollections.observableArrayList("Pendiente", "En progreso", "Completo"));

        tareas = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Configurar el cell factory para la columna colEstado
        colEstado.setCellFactory(new Callback<TableColumn<Tarea, String>, TableCell<Tarea, String>>() {
            @Override
            public TableCell<Tarea, String> call(TableColumn<Tarea, String> tareaStringTableColumn) {
                return new TableCell<Tarea, String>() {
                    @Override
                    protected void updateItem(String estado, boolean empty) {
                        super.updateItem(estado, empty);
                        if (empty || estado == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(estado);

                            // Cambiar el color de fondo y la letra según el estado
                            if ("Pendiente".equals(estado)) {
                                setTextFill(Color.web("#660000")); // Rojo oscuro
                                setStyle("-fx-background-color: #FFCCCC;"); // Rojo claro
                            } else if ("En progreso".equals(estado)) {
                                setTextFill(Color.web("#663300")); // Naranja oscuro
                                setStyle("-fx-background-color: #FFD699;"); // Naranja claro
                            } else if ("Completo".equals(estado)) {
                                setTextFill(Color.web("#006600")); // Verde oscuro
                                setStyle("-fx-background-color: #CCFFCC;"); // Verde claro
                            }
                        }
                    }
                };
            }
        });
    }
    @FXML
    void abrir(ActionEvent event) {
        Tarea tareaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nuevo.fxml"));
            try {
                AnchorPane nuevoContent = loader.load();
                Nuevo nuevoController = loader.getController();
                nuevoController.mostrarTarea(tareaSeleccionada); // Método para mostrar la tarea en nuevo.fxml
                anchorPanePrincipal.getChildren().setAll(nuevoContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una tarea para abrir");
            alert.showAndWait();
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        try {
            String titulo = this.tfTitulo.getText();
            String categoria = this.tfCategoria.getText();
            LocalDate fecha = this.datePicker.getValue();
            String estado = comboEstado.getValue(); // Obtener el estado del ComboBox

            Tarea t = new Tarea(id++, titulo, categoria, fecha, estado);

            if (!this.tareas.contains(t)) {
                this.tareas.add(t);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Este elemento ya existe");
                alert.showAndWait();
            }
            // Actualizar la tabla
            this.tabla.setItems(tareas);
            limpiarCampos();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

    @FXML
    void editar(ActionEvent event) {
        // Obtener la tarea seleccionada en la tabla
        Tarea tareaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una tarea para editar");
            alert.showAndWait();
            return;
        }

        // Actualizar los datos de la tarea seleccionada
        tareaSeleccionada.setTitulo(tfTitulo.getText());
        tareaSeleccionada.setCategoria(tfCategoria.getText());
        tareaSeleccionada.setFecha(datePicker.getValue());
        tareaSeleccionada.setEstado(comboEstado.getValue());

        // Actualizar la tabla
        tabla.refresh();
        limpiarCampos();
    }
    @FXML
    void borrar(ActionEvent event) {
        // Obtener la tarea seleccionada en la tabla
        Tarea tareaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            // Eliminar la tarea de la lista de tareas
            tareas.remove(tareaSeleccionada);
            // Actualizar la tabla
            tabla.setItems(tareas);
            limpiarCampos();
        } else {
            // Si no se ha seleccionado ninguna tarea, mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una tarea para borrar");
            alert.showAndWait();
        }
    }

    // Método para limpiar todos los campos
    private void limpiarCampos() {
        tfCategoria.clear();
        tfTitulo.clear();
        datePicker.setValue(null);
        comboEstado.getSelectionModel().clearSelection();
    }
    public int contarCompletas() {
        int count = 0;
        for (Tarea tarea : tareas) {
            if ("Completo".equals(tarea.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public int contarEnProgreso() {
        int count = 0;
        for (Tarea tarea : tareas) {
            if ("En progreso".equals(tarea.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public int contarPendientes() {
        int count = 0;
        for (Tarea tarea : tareas) {
            if ("Pendiente".equals(tarea.getEstado())) {
                count++;
            }
        }
        return count;
    }
    public AnchorPane getAnchorPanePrincipal() {
        return anchorPanePrincipal;
    }
}
