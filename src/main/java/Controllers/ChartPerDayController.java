package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class ChartPerDayController {

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        // Konfigurimi i X boshtit (dita)
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(30);
        xAxis.setTickUnit(1);

        // Konfigurimi i Y boshtit (rezervime)
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(37);
        yAxis.setTickUnit(1);

        XYChart.Series<Number, Number> data = new XYChart.Series<>();
        data.setName("Rezervime");

        for (int i = 1; i <= 30; i++) {
            int randomValue = (int)(Math.random() * 36 + 1);
            data.getData().add(new XYChart.Data<>(i, randomValue));
        }

        lineChart.getData().add(data);
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
