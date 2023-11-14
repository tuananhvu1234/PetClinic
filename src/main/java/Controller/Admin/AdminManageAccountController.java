package Controller.Admin;

import Entities.AccountEntity;
import Entities.DoctorEntity;
import Entities.PetEntity;
import Entities.UserEntity;
import Models.AccountModel;
import Models.DoctorLevelModel;
import Models.PetModel;
import Models.UserModel;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminManageAccountController implements Initializable {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfFullName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private DatePicker dpDob;

    @FXML
    private RadioButton male, female, other;

    @FXML
    private TextField tfTitle, tfPetName, tfPetAge, tfPetWeight;

    @FXML
    private RadioButton rbDog, rbCat, rbPetMale, rbPetFemale, rbPetOther;

    @FXML
    private ComboBox<String> comboBoxAccount;

    @FXML
    private TableView<AccountModel> tblAccount;

    @FXML
    private TableColumn<AccountModel, String> colUsername;

    @FXML
    private TableColumn<AccountModel, String> colPassword;

    @FXML
    private TableColumn<AccountModel, Integer> colStatus;

    private final AccountEntity accountEntity = new AccountEntity();
    private final UserEntity userEntity = new UserEntity();
    private final DoctorEntity doctorEntity = new DoctorEntity();
    private final PetEntity petEntity = new PetEntity();

    private ObservableList<AccountModel> listAccount;
    private ObservableList<String> listType;
    private final Date today = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

    int myIndex;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxValue();
        refreshTable();
    }

    private void setTableData(ArrayList<AccountModel> list) {
        listAccount = FXCollections.observableArrayList(list);
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblAccount.setItems(listAccount);
    }

    private void setComboBoxValue() {
        listType = FXCollections.observableArrayList("All", "Doctor", "Client");
        comboBoxAccount.setItems(listType);
        comboBoxAccount.getSelectionModel().selectFirst();
    }

    @FXML
    private void showDetail() {
        UserModel user = getRowData();
        DoctorLevelModel doctor = getRowDoctor();
        PetModel pet = getRowPet();
        if (user != null) {
            tfId.setText(String.valueOf(user.getUserId()));
            tfFullName.setText(user.getFullName());
            tfEmail.setText(user.getEmail());
            tfPhone.setText(user.getPhoneNumber());
            tfAddress.setText(user.getAddress());
            dpDob.setValue(user.getDob().toLocalDate());
            tfUsername.setText(user.getAccount().getUsername());
            tfPassword.setText(user.getAccount().getPassword());

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

            if (doctor != null) {
                tfTitle.setText(doctor.getTitle());
            } else {
                tfTitle.setText("");
            }

            if (pet != null) {
                tfPetName.setText(pet.getPetName());
                tfPetWeight.setText(String.valueOf(pet.getPetWeight()));
                tfPetAge.setText(String.valueOf(pet.getPetAge()));
                if (pet.getPetType().toLowerCase().equals("dog")) {
                    rbDog.setSelected(true);
                }
                if (pet.getPetType().toLowerCase().equals("cat")) {
                    rbCat.setSelected(true);
                }
                switch (pet.getPetGender()) {
                    case 0:
                        rbPetMale.setSelected(true);
                        break;
                    case 1:
                        rbPetFemale.setSelected(true);
                        break;
                    default:
                        rbPetOther.setSelected(true);
                        break;
                }
            } else {
                tfPetName.setText("");
                tfPetWeight.setText("");
                tfPetAge.setText("");
                rbDog.setSelected(false);
                rbCat.setSelected(false);
                rbPetMale.setSelected(false);
                rbPetFemale.setSelected(false);
                rbPetOther.setSelected(false);
            }
        }
    }

    @FXML
    private void add() {

        resetData();
        refreshTable();
    }

    @FXML
    private void delete() {
        resetData();
        refreshTable();
    }

    @FXML
    private void resetData() {
        tfId.setText("");
        tfFullName.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        dpDob.setValue(today.toLocalDate());
        tfUsername.setText("");
        tfPassword.setText("");
        male.setSelected(false);
        other.setSelected(false);
        tfTitle.setText("");
        tfPetName.setText("");
        tfPetWeight.setText("");
        tfPetAge.setText("");
        rbDog.setSelected(false);
        rbCat.setSelected(false);
        rbPetMale.setSelected(false);
        rbPetFemale.setSelected(false);
        rbPetOther.setSelected(false);
    }

    @FXML
    private void refreshTable() {
        setTableData((ArrayList<AccountModel>) accountEntity.getAllAccount());
    }

    @FXML
    private UserModel getRowData() {
        AccountModel account = tblAccount.getSelectionModel().getSelectedItem();
        if (account != null) {
            int id = accountEntity.getAccountId(account.getUsername());
            return userEntity.getOneUser(id);
        }
        return null;
    }

    @FXML
    private DoctorLevelModel getRowDoctor() {
        AccountModel account = tblAccount.getSelectionModel().getSelectedItem();
        if (account != null) {
            int id = accountEntity.getAccountId(account.getUsername());
            return (DoctorLevelModel) doctorEntity.getOneDoctorUid(id);
        }
        return null;
    }

    @FXML
    private PetModel getRowPet() {
        AccountModel account = tblAccount.getSelectionModel().getSelectedItem();
        if (account != null) {
            int id = accountEntity.getAccountId(account.getUsername());
            return petEntity.getOnePetUid(id);
        }
        return null;
    }
}
