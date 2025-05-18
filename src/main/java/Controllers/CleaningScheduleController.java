package Controllers;

import Models.CleaningSchedule;
import Models.DTO.CreateCleaningScheduleDTO;
import Models.DTO.UpdateCleaningScheduleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.CleaningScheduleRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CleaningScheduleController {

    @FXML
    private TableView<CleaningSchedule> cleaningTable;

    @FXML
    private TableColumn<CleaningSchedule, Integer> colId;
    @FXML
    private TableColumn<CleaningSchedule, Integer> colRoomId;
    @FXML
    private TableColumn<CleaningSchedule, Integer> colEmployeeId;
    @FXML
    private TableColumn<CleaningSchedule, Date> colDate;
    @FXML
    private TableColumn<CleaningSchedule, String> colStatus;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    @FXML
    private TextField searchBox;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button se;

    private CleaningScheduleRepository repository;
    private ObservableList<CleaningSchedule> cleaningList;

    @FXML
    public void initialize() {
        repository = new CleaningScheduleRepository();

        // Inicializo kolonat e tabelës
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("scheduledDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Inicializojmë ComboBox me opsionet për kërkim
        comboBox.setItems(FXCollections.observableArrayList("id", "roomId", "employeeId", "scheduledDate", "status"));

        // Ngarkojmë të dhënat nga baza
        loadAllCleaning();

        // Event për butonin Search
        se.setOnAction(e -> doSearch());

        // Event për butonin Add
        btnAdd.setOnAction(e -> showAddDialog());

        // Event për butonin Delete
        btnDelete.setOnAction(e -> deleteCleaningById());

        // Event për butonin Edit
        btnUpdate.setOnAction(e -> editCleaningById());
    }

    private void loadAllCleaning() {
        List<CleaningSchedule> all = repository.getAll();
        cleaningList = FXCollections.observableArrayList(all);
        cleaningTable.setItems(cleaningList);
    }

    private void doSearch() {
        String searchTerm = searchBox.getText().trim().toLowerCase();
        String searchBy = comboBox.getValue();

        if (searchBy == null || searchBy.isEmpty()) {
            showAlert("Gabim", "Ju lutem zgjidhni fushën për kërkim.");
            return;
        }

        if (searchTerm.isEmpty()) {
            cleaningTable.setItems(cleaningList);
            return;
        }

        List<CleaningSchedule> filtered = cleaningList.stream().filter(c -> {
            switch (searchBy) {
                case "id":
                    return String.valueOf(c.getId()).contains(searchTerm);
                case "roomId":
                    return String.valueOf(c.getRoom_id()).contains(searchTerm);
                case "employeeId":
                    return String.valueOf(c.getEmployee_id()).contains(searchTerm);
                case "scheduledDate":
                    return c.getScheduled_date().toString().contains(searchTerm);
                case "status":
                    return c.getStatus().toLowerCase().contains(searchTerm);
                default:
                    return false;
            }
        }).collect(Collectors.toList());

        cleaningTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void showAddDialog() {
        Dialog<CreateCleaningScheduleDTO> dialog = new Dialog<>();
        dialog.setTitle("Shto orarin e pastrimit të ri");
        dialog.setHeaderText(null);

        Label lblRoomId = new Label("Room ID:");
        TextField tfRoomId = new TextField();
        Label lblEmployeeId = new Label("Employee ID:");
        TextField tfEmployeeId = new TextField();
        Label lblDate = new Label("Scheduled Date (YYYY-MM-DD):");
        TextField tfDate = new TextField();
        Label lblStatus = new Label("Status:");
        TextField tfStatus = new TextField();

        VBox content = new VBox(10, lblRoomId, tfRoomId, lblEmployeeId, tfEmployeeId, lblDate, tfDate, lblStatus, tfStatus);
        dialog.getDialogPane().setContent(content);

        ButtonType addButtonType = new ButtonType("Shto", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int roomId = Integer.parseInt(tfRoomId.getText().trim());
                    int employeeId = Integer.parseInt(tfEmployeeId.getText().trim());
                    Date scheduledDate = Date.valueOf(tfDate.getText().trim());
                    String status = tfStatus.getText().trim();

                    if (status.isEmpty()) {
                        showAlert("Gabim", "Statusi nuk mund të jetë bosh.");
                        return null;
                    }

                    return new CreateCleaningScheduleDTO(roomId, employeeId, scheduledDate, status);
                } catch (Exception ex) {
                    showAlert("Gabim", "Ju lutem plotësoni të gjitha fushat saktë.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(dto -> {
            CleaningSchedule newCleaning = repository.create(dto);
            if (newCleaning != null) {
                cleaningList.add(newCleaning);
                cleaningTable.refresh();
            } else {
                showAlert("Gabim", "Shtimi i orarit të pastrimit dështoi.");
            }
        });
    }

    @FXML
    private void deleteCleaningById() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Fshi orarin e pastrimit");
        dialog.setHeaderText(null);
        dialog.setContentText("Shkruaj ID-në e orarit që doni të fshini:");

        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr.trim());
                boolean success = repository.delete(id);
                if (success) {
                    cleaningList.removeIf(c -> c.getId() == id);
                    cleaningTable.refresh();
                    showAlert("Sukses", "Orari u fshi me sukses.");
                } else {
                    showAlert("Gabim", "Nuk u gjet orari me këtë ID.");
                }
            } catch (NumberFormatException e) {
                showAlert("Gabim", "ID-ja duhet të jetë numër i saktë.");
            }
        });
    }

    @FXML
    private void editCleaningById() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Edito orarin e pastrimit");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Shkruaj ID-në e orarit që doni të editoni:");

        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr.trim());
                CleaningSchedule cleaning = cleaningList.stream()
                        .filter(c -> c.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (cleaning == null) {
                    showAlert("Gabim", "Nuk u gjet orari me këtë ID.");
                    return;
                }

                Dialog<UpdateCleaningScheduleDTO> editDialog = new Dialog<>();
                editDialog.setTitle("Përditëso orarin e pastrimit");
                editDialog.setHeaderText(null);

                Label lblStatus = new Label("Statusi i ri:");
                ComboBox<String> cbStatus = new ComboBox<>();
                cbStatus.getItems().addAll("Scheduled", "In Progress", "Completed");
                cbStatus.setValue(cleaning.getStatus());

                Label lblEmployee = new Label("ID i punëtorit:");
                TextField txtEmployee = new TextField(String.valueOf(cleaning.getEmployee_id()));

                Label lblDate = new Label("Data e re:");
                DatePicker datePicker = new DatePicker(cleaning.getScheduled_date().toLocalDate());

                VBox content = new VBox(10, lblStatus, cbStatus, lblEmployee, txtEmployee, lblDate, datePicker);
                editDialog.getDialogPane().setContent(content);

                ButtonType updateButtonType = new ButtonType("Përditëso", ButtonBar.ButtonData.OK_DONE);
                editDialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                editDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == updateButtonType) {
                        String newStatus = cbStatus.getValue();
                        String employeeStr = txtEmployee.getText().trim();
                        LocalDate localDate = datePicker.getValue();

                        if (newStatus == null || newStatus.isEmpty() || employeeStr.isEmpty() || localDate == null) {
                            showAlert("Gabim", "Të gjitha fushat duhet të plotësohen.");
                            return null;
                        }

                        try {
                            int newEmployeeId = Integer.parseInt(employeeStr);
                            Date newDate = Date.valueOf(localDate);
                            return new UpdateCleaningScheduleDTO(id, newEmployeeId, newDate, newStatus);
                        } catch (NumberFormatException e) {
                            showAlert("Gabim", "ID e punëtorit duhet të jetë numër.");
                        }
                    }
                    return null;
                });

                editDialog.showAndWait().ifPresent(updateDto -> {
                    CleaningSchedule updated = repository.update(updateDto);
                    if (updated != null) {
                        int index = cleaningList.indexOf(cleaning);
                        cleaningList.set(index, updated);
                        cleaningTable.refresh();
                        showAlert("Sukses", "Orari me ID " + id + " u përditësua me sukses.");
                    } else {
                        showAlert("Gabim", "Përditësimi dështoi.");
                    }
                });

            } catch (NumberFormatException e) {
                showAlert("Gabim", "ID-ja duhet të jetë numër i saktë.");
            }
        });
    }

    @FXML
    private void handleback(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainMaintenance.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Kryesore");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Gabim", "Nuk mund të kthehem te menuja kryesore.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
