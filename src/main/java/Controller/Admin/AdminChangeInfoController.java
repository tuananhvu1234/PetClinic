package Controller.Admin;

import Entities.AccountEntity;
import Entities.UserEntity;
import Models.AccountModel;
import Models.UserModel;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AdminChangeInfoController implements Initializable {

    @FXML
    private TextField tfFullName;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfConfirm;

    @FXML
    private RadioButton rbMale, rbFemale, rbOther;

    @FXML
    private DatePicker dpDob;

    private final UserEntity userEntity = new UserEntity();
    private final AccountEntity accountEntity = new AccountEntity();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
    }

    @FXML
    private void getData() {
        UserModel user = userEntity.getSessionUser();
        tfFullName.setText(user.getFullName());
        tfPhone.setText(user.getPhoneNumber());
        tfEmail.setText(user.getEmail());
        tfAddress.setText(user.getAddress());
        tfUsername.setText(user.getAccount().getUsername());
        tfPassword.setText(user.getAccount().getPassword());
        tfConfirm.setText(user.getAccount().getPassword());
        dpDob.setValue(user.getDob().toLocalDate());
        switch (user.getGender()) {
            case 0:
                rbMale.setSelected(true);
                break;
            case 1:
                rbFemale.setSelected(true);
                break;
            default:
                rbOther.setSelected(true);
                break;
        }
    }

    @FXML
    private void add() {
        int id = userEntity.getSessionUser().getUserId();
        int role = userEntity.getSessionUser().getRole();
        String fullName = tfFullName.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String address = tfAddress.getText();
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        int gender;
        if (rbMale.isSelected()) {
            gender = 0;
        } else if (rbFemale.isSelected()) {
            gender = 1;
        } else {
            gender = 2;
        }
        Date dob = Date.valueOf(dpDob.getValue());
        AccountModel acc = new AccountModel(username, password);
        UserModel user = new UserModel(id, role, fullName, gender, dob, email, address, phone, acc);
        userEntity.updateUser(user);
        accountEntity.updateAccount(username, password);
    }
}
