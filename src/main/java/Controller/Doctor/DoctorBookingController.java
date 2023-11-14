package Controller.Doctor;

import Controller.Router;
import Entities.*;
import Models.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Acer
 */
public class DoctorBookingController implements Initializable {

    @FXML
    private TextField inputSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField petName;

    @FXML
    private TextField petType;

    @FXML
    private TextField petWeight;

    @FXML
    private TextField petAge;

    @FXML
    private RadioButton male, female;

    @FXML
    private TableView<BookingModel> doctorBooking;

    @FXML
    private TableColumn<BookingModel, String> bookingId;

    @FXML
    private TableColumn<BookingModel, String> fullName;

    @FXML
    private TableColumn<BookingModel, String> serviceName;

    @FXML
    private TableColumn<BookingModel, String> bookingDate;

    @FXML
    private TableColumn<BookingModel, String> bookingTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }

    @FXML
    private void Reset() {
        inputSearch.setText(null);
        petName.setText(null);
        petType.setText(null);
        petWeight.setText(null);
        petAge.setText(null);
        male.setSelected(false);
        female.setSelected(false);
    }

    @FXML
    private void Refesh() {
        Reset();
        initData();
    }

    @FXML
    private void initData() {
        BookingEntity bke = new BookingEntity();
        ObservableList<BookingModel> bms = FXCollections.observableArrayList(bke.getAllBooking());

        table(bms);
    }

    @FXML
    private void search() {
        String cusName = inputSearch.getText();

        BookingEntity bke = new BookingEntity();
        ObservableList<BookingModel> bms = FXCollections.observableArrayList(bke.getAllWithCustomerName(cusName));

        table(bms);
    }

    @FXML
    private void table(ObservableList<BookingModel> bms) {
        doctorBooking.setItems(bms);
        bookingId.setCellValueFactory(f -> {
            StringProperty bookingId = new SimpleStringProperty();

            bookingId.setValue(String.valueOf(f.getValue().getBookingId()));
            return bookingId;
        });
        fullName.setCellValueFactory(f -> {
            StringProperty fullName = new SimpleStringProperty();

            int userId = f.getValue().getUserId();
            UserEntity ue = new UserEntity();
            UserModel um = ue.getOneUser(userId);

            fullName.setValue(String.valueOf(um.getFullName()));
            return fullName;
        });
        serviceName.setCellValueFactory(f -> {
            StringProperty serviceName = new SimpleStringProperty();

            int serviceId = f.getValue().getServiceId();
            ServiceEntity se = new ServiceEntity();
            ServiceModel sm = se.getOneService(serviceId);

            serviceName.setValue(sm.getServiceName());
            return serviceName;
        });
        bookingDate.setCellValueFactory(f -> {
            StringProperty bookingDate = new SimpleStringProperty();

            bookingDate.setValue(f.getValue().getBookingDate().toString());
            return bookingDate;
        });
        bookingTime.setCellValueFactory(f -> {
            StringProperty bookingTime = new SimpleStringProperty();

            bookingTime.setValue(f.getValue().getBookingTime().toString());
            return bookingTime;
        });

        doctorBooking.setRowFactory(tv -> {
            TableRow<BookingModel> myRow = new TableRow<>();

            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = doctorBooking.getSelectionModel().getSelectedIndex();

                    int petId = doctorBooking.getItems().get(myIndex).getPetId();
                    PetEntity pe = new PetEntity();
                    PetModel pm = pe.getOnePet(petId);

                    petName.setText(pm.getPetName());
                    petType.setText(pm.getPetType());

                    int gender = pm.getPetGender();

                    if (gender == 0) {
                        male.setSelected(true);
                    } else {
                        female.setSelected(true);
                    }

                    petWeight.setText(String.valueOf(pm.getPetWeight()));
                    petAge.setText(String.valueOf(pm.getPetAge()));
                }
            });

            return myRow;
        });
    }
}
