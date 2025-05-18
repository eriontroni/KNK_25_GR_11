package Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

public class ReceptionDashboardController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblUserPerRole;

    @FXML
    private Label lblReservationsPerDay;

    @FXML
    private BarChart<String, Number> barChartUsers;

    @FXML
    private LineChart<String, Number> lineChartReservations;

    @FXML
    private void initialize() {
        // Mund të shti këtu kodin për me mbush grafikat me të dhëna
        System.out.println("Dashboard u inicializua.");

        // Shembull: titulli me u bë dinamik
        lblTitle.setText("Paneli i Rezervimeve");

        // Këtu mund të inicializosh grafikat përmes metodave të tjera nëse ke të dhëna nga databaza
    }
}
