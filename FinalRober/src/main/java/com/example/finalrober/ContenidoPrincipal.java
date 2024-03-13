package com.example.finalrober;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class ContenidoPrincipal {

    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private Label lbCompletas;
    @FXML
    private Label lbEnProgreso;
    @FXML
    private Label lbPendiente;
    @FXML
    private Label lbTotal;
    @FXML
    private PieChart pieChart;
    private VerTodo verTodo;

    public void setVerTodo(VerTodo verTodo){
        this.verTodo = verTodo;
    }

    @FXML
    public void initialize() {

        anchorPanePrincipal.getStylesheets().add(getClass().getResource("piechart.css").toExternalForm());

        // Obtener los valores de los Labels y convertirlos a double
        double completas = Double.parseDouble(lbCompletas.getText());
        double enProgreso = Double.parseDouble(lbEnProgreso.getText());
        double pendientes = Double.parseDouble(lbPendiente.getText());
        double total = completas + enProgreso + pendientes;

        // Calcular los porcentajes
        double porcentajeCompletas = (completas / total) * 100;
        double porcentajeEnProgreso = (enProgreso / total) * 100;
        double porcentajePendientes = (pendientes / total) * 100;

        // Limpiar los datos previos del PieChart
        pieChart.getData().clear();

        // Agregar los datos al PieChart con los colores personalizados
        PieChart.Data completaData = new PieChart.Data("Completas", porcentajeCompletas);
        PieChart.Data enProgresoData = new PieChart.Data("En Progreso", porcentajeEnProgreso);
        PieChart.Data pendientesData = new PieChart.Data("Pendientes", porcentajePendientes);

        // Agregar los datos al PieChart
        pieChart.getData().addAll(completaData, enProgresoData, pendientesData);

        // Establecer estilos después de agregar los datos al PieChart
        completaData.getNode().setStyle("-fx-pie-color: #87ECAF;");
        enProgresoData.getNode().setStyle("-fx-pie-color: #96C9D1;");
        pendientesData.getNode().setStyle("-fx-pie-color: #FB958D;");

        // Cambiar el color de la leyenda
        for (PieChart.Data data : pieChart.getData()) {
            switch (data.getName()) {
                case "Completas":
                    data.getNode().setStyle("-fx-pie-color: #87ECAF;");
                    break;
                case "En Progreso":
                    data.getNode().setStyle("-fx-pie-color: #96C9D1;");
                    break;
                case "Pendientes":
                    data.getNode().setStyle("-fx-pie-color: #FB958D;");
                    break;
            }
        }
        actualizarEtiquetas();
    }
    private void actualizarEtiquetas() {
        if (verTodo != null) {
            int completas = verTodo.contarCompletas();
            int enProgreso = verTodo.contarEnProgreso();
            int pendientes = verTodo.contarPendientes();

            lbCompletas.setText(String.valueOf(completas));
            lbEnProgreso.setText(String.valueOf(enProgreso));
            lbPendiente.setText(String.valueOf(pendientes));

            // Calcular el total y actualizar la etiqueta de total
            int total = completas + enProgreso + pendientes;
            lbTotal.setText(String.valueOf(total));
        }
    }
}
