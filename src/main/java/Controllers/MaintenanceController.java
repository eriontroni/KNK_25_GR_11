package Controllers;

import Models.Maintenance;
import Models.DTO.CreateMaintenanceDTO;
import Models.DTO.UpdateMaintenanceDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.MaintenanceRepository;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MaintenanceController {

    @FXML
    private TableView<Maintenance> maintenanceTable;

    @FXML
    private TableColumn<Maintenance, Integer> maintenanceIdColumn;
    @FXML
    private TableColumn<Maintenance, Integer> roomIdColumn;
    @FXML
    private TableColumn<Maintenance, Integer> reportedByColumn;
    @FXML
    private TableColumn<Maintenance, String> descriptionColumn;
    @FXML
    private TableColumn<Maintenance, String> statusColumn;
    @FXML
    private TableColumn<Maintenance, Date> reportedAtColumn;

    @FXML
    private Button addbutton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchByComboBox;
    @FXML
    private Button searchButton;

    private MaintenanceRepository repository;
    private ObservableList<Maintenance> maintenanceList;

    @FXML
    public void initialize() {
        repository = new MaintenanceRepository();

        // Inicializimi i kolonave te tabeles
        maintenanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        reportedByColumn.setCellValueFactory(new PropertyValueFactory<>("reportedBy"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        reportedAtColumn.setCellValueFactory(new PropertyValueFactory<>("reportedAt"));

        loadAllMaintenance();
        
        // Event per butonin Add - hap nje dialog per futjen e te dhenave te reja
        addbutton.setOnAction(e -> showAddDialog());

        // Event per butonin Delete - fshin selektimin ne tabele
        deleteButton.setOnAction(e -> deleteMaintenanceById());

    }

    private void loadAllMaintenance() {
        List<Maintenance> all = repository.getAll();
        maintenanceList = FXCollections.observableArrayList(all);
        maintenanceTable.setItems(maintenanceList);
    }

    private void showAddDialog() {
        // Form i thjeshtë për futjen e mirëmbajtjes së re
        Dialog<CreateMaintenanceDTO> dialog = new Dialog<>();
        dialog.setTitle("Shto mirëmbajtje të re");
        dialog.setHeaderText(null);

        Label lblRoomId = new Label("Room ID:");
        TextField tfRoomId = new TextField();
        Label lblReportedBy = new Label("Reported By:");
        TextField tfReportedBy = new TextField();
        Label lblDescription = new Label("Description:");
        TextField tfDescription = new TextField();
        Label lblStatus = new Label("Status:");
        TextField tfStatus = new TextField();
        Label lblReportedAt = new Label("Reported At (YYYY-MM-DD):");
        TextField tfReportedAt = new TextField();

        VBox content = new VBox(10, lblRoomId, tfRoomId, lblReportedBy, tfReportedBy, lblDescription, tfDescription, lblStatus, tfStatus, lblReportedAt, tfReportedAt);
        dialog.getDialogPane().setContent(content);

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int roomId = Integer.parseInt(tfRoomId.getText().trim());
                    int reportedBy = Integer.parseInt(tfReportedBy.getText().trim());
                    String description = tfDescription.getText().trim();
                    String status = tfStatus.getText().trim();
                    Date reportedAt = Date.valueOf(tfReportedAt.getText().trim());

                    return new CreateMaintenanceDTO(roomId, reportedBy, description, status, reportedAt);
                } catch (Exception ex) {
                    showAlert("Gabim", "Ju lutem plotësoni të gjitha fushat saktë.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(dto -> {
            Maintenance newMaintenance = repository.create(dto);
            if (newMaintenance != null) {
                maintenanceList.add(newMaintenance);
                maintenanceTable.refresh();
            } else {
                showAlert("Gabim", "Shtimi i mirëmbajtjes dështoi.");
            }
        });
    }

    private void deleteMaintenanceById() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Fshi mirëmbajtjen");
        dialog.setHeaderText(null);
        dialog.setContentText("Shkruaj ID-në e mirëmbajtjes që doni të fshini:");

        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr.trim());
                boolean success = repository.delete(id);
                if (success) {
                    maintenanceList.removeIf(m -> m.getId() == id);
                    maintenanceTable.refresh();
                    showAlert("Sukses", "Mirëmbajtja u fshi me sukses.");
                } else {
                    showAlert("Gabim", "Nuk u gjet mirëmbajtja me këtë ID.");
                }
            } catch (NumberFormatException e) {
                showAlert("Gabim", "ID-ja duhet të jetë numër i saktë.");
            }
        });
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
