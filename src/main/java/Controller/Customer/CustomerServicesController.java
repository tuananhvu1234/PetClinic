/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Customer;

import Entities.ServiceEntity;
import Models.ServiceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Acer
 */
public class CustomerServicesController implements Initializable {

    @FXML
    private TextField inputSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<ServiceModel> tableService;

    @FXML
    private TableColumn<ServiceModel, Integer> ColsId;

    @FXML
    private TableColumn<ServiceModel, String> ColsType;

    @FXML
    private TableColumn<ServiceModel, String> ColsName;

    @FXML
    private TableColumn<ServiceModel, Float> ColsPrice;

    private final ServiceEntity serviceEntity = new ServiceEntity();

    private ObservableList<ServiceModel> listService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData(serviceEntity.getAllService());
    }

    private void setTableData(ArrayList<ServiceModel> list) {
        listService = FXCollections.observableArrayList(list);
        ColsId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        ColsName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        ColsType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        ColsPrice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        tableService.setItems(listService);
    }

    @FXML
    private void searchEvent() {
        String input = inputSearch.getText();
        String name = "", type = "";
        if (!input.isEmpty()) {
            name = input;
        }
        setTableData(serviceEntity.getAllService(name, type));
    }
}
