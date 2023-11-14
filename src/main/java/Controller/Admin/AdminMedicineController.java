package Controller.Admin;

import Entities.MedicationEntity;
import Models.MedicationModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminMedicineController implements Initializable {

    @FXML
    private TextField tfMedicineId;

    @FXML
    private TextField tfMedicineName;

    @FXML
    private TextField tfMedicineDescription;

    @FXML
    private TextField tfMedicinePrice;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<MedicationModel> tableMedicine;

    @FXML
    private TableColumn<MedicationModel, Integer> colMId;

    @FXML
    private TableColumn<MedicationModel, String> colMName;

    @FXML
    private TableColumn<MedicationModel, String> colMDescription;

    @FXML
    private TableColumn<MedicationModel, Float> colMPrice;

    private final MedicationEntity medicationEntity = new MedicationEntity();
    private ObservableList<MedicationModel> listBooking;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }

    @FXML
    private void setTableData(ArrayList<MedicationModel> list) {
        listBooking = FXCollections.observableArrayList(list);
        colMId.setCellValueFactory(new PropertyValueFactory<>("medicationId"));
        colMName.setCellValueFactory(new PropertyValueFactory<>("medicationName"));
        colMDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableMedicine.setItems(listBooking);
    }

    @FXML
    private MedicationModel getRowData() {
        return tableMedicine.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void searchEvent() {
        String input = tfSearch.getText();
        if (!input.isEmpty()) {
            setTableData(medicationEntity.getAllMedication(input));
        } else {
            refreshTable();
        }
    }

    @FXML
    private void showDetail() {
        MedicationModel medication = getRowData();
        if (medication != null) {
            tfMedicineId.setText(String.valueOf(medication.getMedicationId()));
            tfMedicineName.setText(medication.getMedicationName());
            tfMedicineDescription.setText(medication.getDescription());
            tfMedicinePrice.setText(String.valueOf(medication.getPrice()));
        }
    }

    @FXML
    private void add() {
        String id = tfMedicineId.getText();
        String name = tfMedicineName.getText();
        String description = tfMedicineDescription.getText();
        String price = tfMedicinePrice.getText();
        if (id.isEmpty()) {
            //add
            if (!name.isEmpty()
                    && !description.isEmpty()
                    && !price.isEmpty()) {
                medicationEntity.addMedication(new MedicationModel(name, description, Float.valueOf(price)));
            }
        } else {
            //update
            if (!name.isEmpty()
                    && !description.isEmpty()
                    && !price.isEmpty()) {
                medicationEntity.updateMedication(
                        new MedicationModel(Integer.valueOf(id), name, description, Float.valueOf(price)));
            }
        }
        resetData();
        refreshTable();
    }

    @FXML
    private void delete() {
        if (getRowData() != null) {
            medicationEntity.deleteMedication(getRowData().getMedicationId());
        }
        resetData();
        refreshTable();
    }

    @FXML
    private void resetData() {
        tfMedicineId.setText("");
        tfMedicineName.setText("");
        tfMedicineDescription.setText("");
        tfMedicinePrice.setText("");
    }

    @FXML
    private void refreshTable() {
        setTableData(medicationEntity.getAllMedication());
    }
}
