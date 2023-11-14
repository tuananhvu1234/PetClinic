package Controller.Customer;

import Entities.BookingEntity;
import Entities.DoctorEntity;
import Entities.PetEntity;
import Entities.ServiceEntity;
import Entities.UserEntity;
import Models.BookingModel;
import Models.ServiceModel;
import Models.UserModel;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class CustomerBookingController implements Initializable {

    @FXML
    private ComboBox<String> chooseDoctor;

    @FXML
    private ComboBox<String> chooseStype;

    @FXML
    private ComboBox<String> listName;

    @FXML
    private TextArea listServiceChose;

    @FXML
    private DatePicker bookingDate;

    @FXML
    private RadioButton morning;

    @FXML
    private RadioButton afternoon;

    private final DoctorEntity doctorEntity = new DoctorEntity();
    private final ServiceEntity serviceEntity = new ServiceEntity();
    private final UserEntity userEntity = new UserEntity();
    private final PetEntity petEntity = new PetEntity();
    private final BookingEntity bookingEntity = new BookingEntity();

    private ObservableList listDoctor;
    private ObservableList listServiceType;
    private ObservableList listServiceName;
    private String listChose = "";
    private final List<Integer> listSId = new ArrayList<>();
    private final Date today = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookingDate.setValue(today.toLocalDate());
        setListDoctor(doctorEntity.getAllDoctor());
        setListType();
        setListName();
    }

    @FXML
    private void getListDoctor() {
        Date bDate = Date.valueOf(bookingDate.getValue());
//        setListDoctor(doctorEntity.getAllDoctorNoSchedual(bDate));
    }

    @FXML
    private void getChoseService() {
        String service = listName.getValue();
        if (!service.toLowerCase().equals("any")) {
            listChose += service + ", ";
            listSId.add(serviceEntity.getServiceId(service));
        }
        listServiceChose.setText(listChose);
    }

    @FXML
    private void add() {
        int serviceId;
        int userId = userEntity.getSessionUser().getUserId();
        int petId = petEntity.getOnePetUid(userId).getPetId();
        Date bDate = Date.valueOf(bookingDate.getValue());
        String time;
        if (morning.isSelected()) {
            time = morning.getText();
        } else if (afternoon.isSelected()) {
            time = afternoon.getText();
        } else {
            time = "";
        }
        float price;
        for (int sid : listSId) {
            serviceId = sid;
            price = serviceEntity.getOneService(sid).getServicePrice();
            BookingModel booking = new BookingModel(serviceId, userId, petId, bDate, time, today, price);
            bookingEntity.addBooking(booking);
        }
        reset();
    }

    @FXML
    private void reset() {
        bookingDate.setValue(today.toLocalDate());
        morning.setSelected(false);
        afternoon.setSelected(false);
        chooseDoctor.getSelectionModel().selectFirst();
        chooseStype.getSelectionModel().selectFirst();
        listName.getSelectionModel().selectFirst();
        listServiceChose.setText("");
    }

    @FXML
    private void setListDoctor(ArrayList<UserModel> listD) {
        List<String> list = new ArrayList<>();
        list.add("Any");
        for (UserModel doctor : listD) {
            list.add(doctor.getFullName());
        }
        listDoctor = FXCollections.observableArrayList(list);
        chooseDoctor.setItems(listDoctor);
        chooseDoctor.getSelectionModel().selectFirst();
    }

    @FXML
    private void setListType() {
        listServiceType = FXCollections.observableArrayList("Any", "Spa", "Health Check");
        chooseStype.setItems(listServiceType);
        chooseStype.getSelectionModel().selectFirst();
    }

    @FXML
    private void setListName() {
        List<String> list = new ArrayList<>();
        list.add("Any");
        for (ServiceModel service : serviceEntity.getAllService()) {
            list.add(service.getServiceName());
        }
        listServiceName = FXCollections.observableArrayList(list);
        listName.setItems(listServiceName);
        listName.getSelectionModel().selectFirst();
    }
}
