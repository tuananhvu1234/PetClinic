package Controller.Admin;

import Entities.BookingEntity;
import Models.BookingModel;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminBookingController implements Initializable {

    @FXML
    private DatePicker datePick;

    @FXML
    private TextField inputSearch;

    @FXML
    private ComboBox<String> comboBoxTime;

    @FXML
    private TableView tableBooking;

    @FXML
    private TableColumn<BookingModel, Integer> bookingId;

    @FXML
    private TableColumn<BookingModel, Integer> userId;

    @FXML
    private TableColumn<BookingModel, Integer> petId;

    @FXML
    private TableColumn<BookingModel, Integer> serviceId;

    @FXML
    private TableColumn<BookingModel, Float> price;

    @FXML
    private TableColumn<BookingModel, Date> bookingDate;

    @FXML
    private TableColumn<BookingModel, String> bookingTime;

    @FXML
    private TableColumn<BookingModel, Date> updateDate;

    private final BookingEntity bookingEntity = new BookingEntity();

    private ObservableList<BookingModel> listBooking;
    private final ObservableList<String> listTime = FXCollections.observableArrayList("All", "Morning", "Afternoon");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData(bookingEntity.getAllBooking());
        comboBoxTime.setItems(listTime);
        comboBoxTime.getSelectionModel().selectFirst();
    }

    @FXML
    private void setTableData(ArrayList<BookingModel> list) {
        listBooking = FXCollections.observableArrayList(list);
        bookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        petId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        serviceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        bookingTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
        updateDate.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        tableBooking.setItems(listBooking);
    }

    @FXML
    private void searchEvent() {
        String user = inputSearch.getText();
        LocalDate inputDate = datePick.getValue();
        String cTime = comboBoxTime.getValue().toLowerCase();
        Date date = null;
        String time = "";
        if (inputDate != null) {
            date = Date.valueOf(inputDate);
        }
        if (!cTime.equals("all")) {
            time = cTime;
        }
        setTableData(bookingEntity.getAllBooking(user, date, time));
    }
}
