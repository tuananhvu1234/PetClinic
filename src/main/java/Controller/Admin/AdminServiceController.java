package Controller.Admin;

import Entities.ServiceEntity;
import Models.ServiceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminServiceController implements Initializable {

    @FXML
    private TextField tfSearch;

    @FXML
    private ComboBox<String> comboBoxType;

    @FXML
    private TextField tfServiceId;

    @FXML
    private TextField tfServiceName;

    @FXML
    private TextField tfServiceType;

    @FXML
    private TextField tfServicePrice;

    @FXML
    private TableView<ServiceModel> tableService;

    @FXML
    private TableColumn<ServiceModel, Integer> ColSId;

    @FXML
    private TableColumn<ServiceModel, String> ColSName;

    @FXML
    private TableColumn<ServiceModel, String> ColSType;

    @FXML
    private TableColumn<ServiceModel, Float> ColSPrice;

    private final ServiceEntity serviceEntity = new ServiceEntity();

    private ObservableList<ServiceModel> listService;
    private ObservableList<String> listType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxValue();
        comboBoxType.getSelectionModel().selectFirst();
        refreshTable();
    }

    private void setTableData(ArrayList<ServiceModel> list) {
        listService = FXCollections.observableArrayList(list);
        ColSId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        ColSName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        ColSType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        ColSPrice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        tableService.setItems(listService);
    }

    private void setComboBoxValue() {
        listType = FXCollections.observableArrayList("All", "Spa", "Health Check");
        comboBoxType.setItems(listType);
    }

    @FXML
    private void searchEvent() {
        String input = tfSearch.getText();
        String cType = comboBoxType.getValue().toLowerCase();
        String name = "", type = "";
        if (!input.isEmpty()) {
            name = input;
        }
        if (!cType.equals("all")) {
            type = cType;
        }
        setTableData(serviceEntity.getAllService(name, type));
    }

    @FXML
    private void showService() {
        ServiceModel service = getRowData();
        if (service != null) {
            tfServiceId.setText(String.valueOf(service.getServiceId()));
            tfServiceName.setText(service.getServiceName());
            tfServiceType.setText(service.getServiceType());
            tfServicePrice.setText(String.valueOf(service.getServicePrice()));
        }
    }

    @FXML
    private void addService() {
        String id = tfServiceId.getText();
        String name = tfServiceName.getText();
        String type = tfServiceType.getText();
        String price = tfServicePrice.getText();
        ServiceModel service = null;

        if (id.isEmpty()) {
            if (!name.isEmpty() && !type.isEmpty() && !price.isEmpty()) {
                service = new ServiceModel(name, type, Float.valueOf(price));
                serviceEntity.addService(service);
            }
        } else {
            if (!name.isEmpty() && !type.isEmpty() && !price.isEmpty()) {
                service = new ServiceModel(Integer.valueOf(id), name, type, Float.valueOf(price));
                serviceEntity.updateService(service);
            }
        }
        refreshTable();
        resetData();
    }

    @FXML
    private void deleteService() {
        ServiceModel service = getRowData();
        if (service != null) {
            int idSelect = service.getServiceId();
            serviceEntity.deleteServices(idSelect);
        }
        resetData();
        refreshTable();
    }

    @FXML
    private void resetData() {
        tfServiceId.setText("");
        tfServiceName.setText("");
        tfServiceType.setText("");
        tfServicePrice.setText("");
    }

    @FXML
    private void refreshTable() {
        setTableData(serviceEntity.getAllService());
    }

    @FXML
    private ServiceModel getRowData() {
        ServiceModel service = tableService.getSelectionModel().getSelectedItem();
        return service;
    }
}
