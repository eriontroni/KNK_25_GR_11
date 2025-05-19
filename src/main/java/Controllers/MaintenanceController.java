package Controllers;

import Models.Maintenance;
import Models.DTO.CreateMaintenanceDTO;
import Models.DTO.UpdateMaintenanceDTO;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.MaintenanceRepository;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


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

        // Inicializo kolonat e tabelës
        maintenanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        reportedByColumn.setCellValueFactory(new PropertyValueFactory<>("reportedBy"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        reportedAtColumn.setCellValueFactory(new PropertyValueFactory<>("reportedAt"));

        // Inicializojmë ComboBox me opsionet për kërkim
        searchByComboBox.setItems(FXCollections.observableArrayList("id", "roomId", "reportedBy", "status", "description", "reportedAt"));

        // Ngarkojmë të dhënat nga baza
        loadAllMaintenance();

        // Event për butonin Search
        searchButton.setOnAction(e -> doSearch());

        // Event për butonin Add - hap një dialog për futjen e të dhënave të reja
        addbutton.setOnAction(e -> showAddDialog());

        // Event për butonin Delete - fshin selektimin në tabelë
        deleteButton.setOnAction(e -> deleteMaintenanceById());

        // Event për butonin Edit - hap dialog për përditësimin e statusit
        editButton.setOnAction(e -> editMaintenanceById());
    }

    private void loadAllMaintenance() {
        List<Maintenance> all = repository.getAll();
        maintenanceList = FXCollections.observableArrayList(all);
        maintenanceTable.setItems(maintenanceList);
    }

    private void doSearch() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        String searchBy = searchByComboBox.getValue();

        if (searchBy == null || searchBy.isEmpty()) {
            showAlert("Gabim", "Ju lutem zgjidhni fushën për kërkim.");
            return;
        }

        if (searchTerm.isEmpty()) {
            // Nëse fusha e kërkimit është bosh, shfaq të gjitha të dhënat
            maintenanceTable.setItems(maintenanceList);
            return;
        }

        List<Maintenance> filtered = maintenanceList.stream().filter(m -> {
            switch (searchBy) {
                case "id":
                    return String.valueOf(m.getId()).contains(searchTerm);
                case "roomId":
                    return String.valueOf(m.getRoomId()).contains(searchTerm);
                case "reportedBy":
                    return String.valueOf(m.getReportedBy()).contains(searchTerm);
                case "status":
                    return m.getStatus().toLowerCase().contains(searchTerm);
                case "description":
                    return m.getDescription().toLowerCase().contains(searchTerm);
                case "reportedAt":
                    return m.getReportedAt().toString().contains(searchTerm);
                default:
                    return false;
            }
        }).collect(Collectors.toList());

        maintenanceTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void handlebback(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        SceneManager.switchScene(stage, "/views/MainMaintenance.fxml", "Menu Kryesore");
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


    private void editMaintenanceById() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Edito mirëmbajtjen");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Shkruaj ID-në e mirëmbajtjes që doni të editoni:");

        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr.trim());
                Maintenance maintenance = maintenanceList.stream()
                        .filter(m -> m.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (maintenance == null) {
                    showAlert("Gabim", "Nuk u gjet mirëmbajtja me këtë ID.");
                    return;
                }

                Dialog<UpdateMaintenanceDTO> editDialog = new Dialog<>();
                editDialog.setTitle("Përditëso mirëmbajtjen");
                editDialog.setHeaderText(null);

                Label lblDescription = new Label("Përshkrimi i ri:");
                TextField tfDescription = new TextField(maintenance.getDescription());

                Label lblStatus = new Label("Statusi i ri:");
                ComboBox<String> cbStatus = new ComboBox<>();
                cbStatus.getItems().addAll("Ne Pritje", "Ne Proces", "Perfunduara");
                cbStatus.setValue(maintenance.getStatus());

                VBox content = new VBox(10, lblDescription, tfDescription, lblStatus, cbStatus);
                editDialog.getDialogPane().setContent(content);

                ButtonType updateButtonType = new ButtonType("Përditëso", ButtonBar.ButtonData.OK_DONE);
                editDialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                editDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == updateButtonType) {
                        String newDescription = tfDescription.getText().trim();
                        String newStatus = cbStatus.getValue();

                        if (newDescription.isEmpty() || newStatus == null || newStatus.isEmpty()) {
                            showAlert("Gabim", "Të dy fushat duhet të plotësohen.");
                            return null;
                        }

                        return new UpdateMaintenanceDTO(id, newDescription, newStatus);
                    }
                    return null;
                });

                editDialog.showAndWait().ifPresent(updateDto -> {
                    Maintenance updated = repository.update(updateDto);
                    if (updated != null) {
                        int index = maintenanceList.indexOf(maintenance);
                        maintenanceList.set(index, updated);
                        maintenanceTable.refresh();
                        showAlert("Sukses", "Mirëmbajtja me ID " + id + " u përditësua me sukses.");
                    } else {
                        showAlert("Gabim", "Përditësimi dështoi.");
                    }
                });

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