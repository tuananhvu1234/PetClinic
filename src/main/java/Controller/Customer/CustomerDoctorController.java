package Controller.Customer;

import Entities.DoctorEntity;
import Models.UserModel;
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

public class CustomerDoctorController implements Initializable {

    @FXML
    private TextField inputSearch;

    @FXML
    private TableView tableDoctor;

    @FXML
    private TableColumn<UserModel, Integer> ColsNo;

    @FXML
    private TableColumn<UserModel, String> ColsName;

    @FXML
    private TableColumn<UserModel, String> ColsEmail;

    @FXML
    private TableColumn<UserModel, String> ColsPhone;

    private final DoctorEntity doctorEntity = new DoctorEntity();

    private ObservableList<UserModel> listDoctor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData(doctorEntity.getAllDoctor());
    }

    private void setTableData(ArrayList<UserModel> list) {
        listDoctor = FXCollections.observableArrayList(list);
        ColsNo.setCellValueFactory(new PropertyValueFactory<>("userId"));
        ColsName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ColsEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColsPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableDoctor.setItems(listDoctor);
    }

    @FXML
    private void searchEvent() {
        String input = inputSearch.getText();
        String name = "";
        if (!input.isEmpty()) {
            name = input;
        }
        setTableData(doctorEntity.getAllDoctor(name));
    }
}
