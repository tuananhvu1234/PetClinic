package Controller.Doctor;

import Entities.AccountEntity;
import Entities.DoctorEntity;
import Entities.UserEntity;
import Models.DoctorLevelModel;
import Models.UserModel;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class DoctorChangeInfoController implements Initializable {

    @FXML
    private TextField fullName;

    @FXML
    private TextField title;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password, confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNumber;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton other;

    private final UserEntity userEntity = new UserEntity();
    private final AccountEntity accountEntity = new AccountEntity();
    private final DoctorEntity doctorEntity = new DoctorEntity();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
    }

    @FXML
    private void getData() {
        UserModel user = userEntity.getSessionUser();
        int id = user.getUserId();
        DoctorLevelModel doctor = (DoctorLevelModel) doctorEntity.getOneDoctorUid(id);
        fullName.setText(user.getFullName());
        title.setText(doctor.getTitle());
        phoneNumber.setText(user.getPhoneNumber());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        username.setText(user.getAccount().getUsername());
        password.setText(user.getAccount().getPassword());
        confirmPassword.setText(user.getAccount().getPassword());
        dob.setValue(user.getDob().toLocalDate());
        switch (user.getGender()) {
            case 0:
                male.setSelected(true);
                break;
            case 1:
                female.setSelected(true);
                break;
            default:
                other.setSelected(true);
                break;
        }
    }

    @FXML
    private void add() {
        int id = userEntity.getSessionUser().getUserId();
        int role = userEntity.getSessionUser().getRole();
        String fullname = fullName.getText();
        String phone = phoneNumber.getText();
        String inemail = email.getText();
        String inaddress = address.getText();
        String inusername = username.getText();
        String inpassword = password.getText();
        int gender;
        if (male.isSelected()) {
            gender = 0;
        } else if (female.isSelected()) {
            gender = 1;
        } else {
            gender = 2;
        }
        Date daob = Date.valueOf(dob.getValue());
        UserModel user = new UserModel(id, role, fullname, gender, daob, inemail, inaddress, phone);
        userEntity.updateUser(user);
        accountEntity.updateAccount(inusername, inpassword);
    }
}
