package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChartPerRoleController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        // Vetëm numra të plotë në boshtin Y
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
            @Override
            public String toString(Number object) {
                return String.format("%d", object.intValue());
            }
        });

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Përdoruesit");

        // Vlerat
        int nrKlient = 15;
        int nrRecepsionist = 7;
        int nrMirmbajtes = 4;

        XYChart.Data<String, Number> klient = new XYChart.Data<>("Klient", nrKlient);
        XYChart.Data<String, Number> recep = new XYChart.Data<>("Recepsionist", nrRecepsionist);
        XYChart.Data<String, Number> miremb = new XYChart.Data<>("Mirëmbajtës", nrMirmbajtes);

        // Vendos numrin në qendër të bar-it
        addCenteredLabel(klient, nrKlient);
        addCenteredLabel(recep, nrRecepsionist);
        addCenteredLabel(miremb, nrMirmbajtes);

        data.getData().addAll(klient, recep, miremb);
        barChart.getData().add(data);
    }

    private void addCenteredLabel(XYChart.Data<String, Number> data, int value) {
        data.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                Text label = new Text(String.valueOf(value));
                label.setStyle("-fx-font-weight: bold; -fx-fill: black;");
                newNode.parentProperty().addListener((o, oldParent, newParent) -> {
                    if (newParent instanceof javafx.scene.Group group) {
                        label.layoutXProperty().bind(newNode.layoutXProperty().add(10));
                        label.layoutYProperty().bind(newNode.layoutYProperty().subtract(10));
                        group.getChildren().add(label);
                    }
                });
            }
        });
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReceptionDashboard.fxml"));
        Scene dashboardScene = new Scene(loader.load());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(dashboardScene);
        stage.setTitle("Dashboard - Recepsionisti");
        stage.show();
    }
}
