package Controller.Admin;

import Entities.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class AdminHomepageController implements Initializable {

    @FXML
    private Text countAppointment;

    @FXML
    private Text countDoctor;

    @FXML
    private Text countClient;

    @FXML
    private Text countAccount;

    private final DoctorEntity doctorEntity = new DoctorEntity();
    private final AccountEntity accountEntity = new AccountEntity();
    private final UserEntity userEntity = new UserEntity();
    private final BookingEntity bookingEntity = new BookingEntity();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer appointment = bookingEntity.getAllBookingInDay().size();
        countAppointment.setText(appointment.toString());
        Integer doctors = doctorEntity.getAllDoctor().size();
        countDoctor.setText(doctors.toString());
        Integer client = userEntity.getAllClient().size();
        countClient.setText(client.toString());
        Integer accounts = accountEntity.getAllAccount().size();
        countAccount.setText(accounts.toString());
    }
}
