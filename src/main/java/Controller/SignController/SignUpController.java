package Controller.SignController;

import Controller.Router;
import Entities.*;
import Models.*;
import Session.SessionWriter;
import Validation.Validate;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SignUpController implements Initializable {

    @FXML
    private TextField inputTextFullName, inputTextUsername, inputTextEmail,
            inputTextAddress, inputTextPhoneNumber;

    @FXML
    private PasswordField inputTextPassword, inputTextConfirmPassword;

    @FXML
    private DatePicker inputDob;

    @FXML
    private RadioButton male, female, other;

    @FXML
    private Label errorFullName, errorUsername, errorPassword, errorConfirmPassword,
            errorDob, errorGender, errorEmail, errorAddress, errorPhoneNumber;

    private final SignEntity signEntity = new SignEntity();
    private final AccountEntity accountEntity = new AccountEntity();
    private final SessionWriter sessionWr = new SessionWriter();

    private final String errorEmptyMessage = "Required";
    private final String errorEmailMessage = "Email Format";
    private final String errorSpaceMessage = "Space Format";
    private final String errorLengthMessage = "This entry can only contain numbers.";
    private final String errorLetterMessage = "This entry can only contain numbers.";
    private final String errorExistAccountMessage = "exist account";
    private final String errorConfirmPasswordMessage = "not match password";
    private final String errorDefaultGenderMessage = "You're selecting other";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputTextFullNameValidate();
        inputTextUsernameValidate();
        inputTextPasswordValidate();
        inputTextConfirmPasswordValidate();
        inputDobValidate();
        inputGenderValidate();
        inputTextEmailValidate();
        inputTextAddressValidate();
        inputTextPhoneNumberValidate();
    }

    //link
    @FXML
    public void goToLogin() {
        Router.switchToSignInPage();
    }

    //sign up
    @FXML
    private void isCompleteForm() {
        if (inputTextFullName.getText().isEmpty()) {
            inputTextFullName.requestFocus();
        } else if (inputTextUsername.getText().isEmpty()) {
            inputTextUsername.requestFocus();
        } else if (inputTextPassword.getText().isEmpty()) {
            inputTextPassword.requestFocus();
        } else if (inputTextConfirmPassword.getText().isEmpty()) {
            inputTextConfirmPassword.requestFocus();
        } else if (inputDob.getValue() == null) {
            inputDob.requestFocus();
        } else if (inputTextEmail.getText().isEmpty()) {
            inputTextEmail.requestFocus();
        } else if (inputTextAddress.getText().isEmpty()) {
            inputTextAddress.requestFocus();
        } else if (inputTextPhoneNumber.getText().isEmpty()) {
            inputTextPhoneNumber.requestFocus();
        } else {
            signUpEvent();
        }
    }

    @FXML
    public void signUpEvent() {
        String fullName = inputTextFullName.getText();
        String username = inputTextUsername.getText();
        String password = inputTextPassword.getText();
        Date dob = Date.valueOf(inputDob.getValue());
        int gender = inputGenderValidate();
        String email = inputTextEmail.getText();
        String address = inputTextAddress.getText();
        String phoneNumber = inputTextPhoneNumber.getText();
        if (signEntity.signUp(
                new UserModel(fullName, gender, dob, email, address, phoneNumber,
                        new AccountModel(username, password))
        )) {
            sceneAlertSuccess();
        }
    }

    //alert
    private void sceneAlertSuccess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        ButtonType loginNow = new ButtonType("Login Now", ButtonBar.ButtonData.OK_DONE);
        ButtonType noThanks = new ButtonType("No, Thanks", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.setTitle("Sign In Succes");
        alert.setHeaderText("Welcom to Pet Clinic");
        alert.setContentText("ban da dang ki thanh cong, ban muon dang nhap luon khong ?");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(noThanks, loginNow);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(loginNow);
        yesButton.setDefaultButton(true);

        Button noButton = (Button) alert.getDialogPane().lookupButton(noThanks);
        noButton.setDefaultButton(false);

        final Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == loginNow) {
            List listLine = Arrays.asList(
                    "remember=" + true,
                    "username=" + inputTextUsername.getText(),
                    "password=" + inputTextPassword.getText()
            );
            sessionWr.setSession(listLine);
            goToLogin();
        }
        if (result.get() == noThanks) {
            alert.close();
        }
    }

    //validation
    @FXML
    private void inputTextFullNameValidate() {
        //empty
        inputTextFullName.setOnKeyPressed((KeyEvent t) -> {
            if (Validate.isEmptyTextField(t, inputTextFullName)) {
                errorFullName.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER && !inputTextFullName.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextFullName.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
                    IntegerProperty currentLength = new SimpleIntegerProperty();
                    currentLength.bind(Bindings.length(inputTextFullName.textProperty()));
                    //empty
                    if (currentLength.getValue() == 0) {
                        errorFullName.setTextFill(Color.RED);
                        errorFullName.setText(errorEmptyMessage);
                    } else {
                        errorFullName.setText(null);
                    }
                });
    }

    @FXML
    private void inputTextUsernameValidate() {
        //empty
        inputTextUsername.setOnKeyPressed((KeyEvent t) -> {
//            if (t.getCode() == KeyCode.ENTER
//                    && inputTextUsername.getText().isEmpty()) {
//                errorUsername.setTextFill(Color.RED);
//                errorUsername.setText(errorEmptyMessage);
//            }
            if (Validate.isEmptyTextField(t, inputTextUsername)) {
                errorUsername.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER && !inputTextUsername.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextUsername.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
//                    IntegerProperty currentLength = new SimpleIntegerProperty();
//                    currentLength.bind(Bindings.length(inputTextUsername.textProperty()));
//                    final String space = " ";
                    //empty
                    if (Validate.isEmptyTextField(inputTextUsername)) {
                        errorUsername.setText(errorEmptyMessage);
                    } //                    else {
                    //                        errorUsername.setText(null);
                    //                    }
                    //                    if (currentLength.getValue() == 0) {
                    //                        errorUsername.setTextFill(Color.RED);
                    //                        errorUsername.setText(errorEmptyMessage);
                    //                    } else {
                    //                        errorUsername.setText(null);
                    //                    }
                    //space
                    else if (Validate.findWhiteSpace(inputTextUsername)) {
//                        Validate.removeWhiteSpace(inputTextUsername);
                        errorUsername.setText(errorSpaceMessage);
                        Validate.removeWhiteSpace(inputTextUsername);
                    } //                    if (inputTextUsername.getText().contains(space)) {
                    //                        int spaceIndex = inputTextUsername.getText().indexOf(space);
                    //                        String str = inputTextUsername.getText().substring(0, spaceIndex);
                    //                        inputTextUsername.setText(str);
                    //                        errorUsername.setTextFill(Color.RED);
                    //                        errorUsername.setText(errorSpaceMessage);
                    //                    }
                    else if (accountEntity.isExistAccount(inputTextUsername.getText())) {
                        errorUsername.setText(errorExistAccountMessage);
                    } else {
                        errorUsername.setText(null);
                    }
                });
    }

    @FXML
    private void inputTextPasswordValidate() {
        //empty
        inputTextPassword.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER
                    && inputTextPassword.getText().isEmpty()) {
                errorPassword.setTextFill(Color.RED);
                errorPassword.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER && !inputTextPassword.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextPassword.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
                    IntegerProperty currentLength = new SimpleIntegerProperty();
                    currentLength.bind(Bindings.length(inputTextPassword.textProperty()));
                    String space = " ";
                    //empty
                    if (currentLength.getValue() == 0) {
                        errorPassword.setTextFill(Color.RED);
                        errorPassword.setText(errorEmptyMessage);
                    } else {
                        errorPassword.setText(null);
                    }
                    //space
                    if (inputTextPassword.getText().contains(space)) {
                        int spaceIndex = inputTextPassword.getText().indexOf(space);
                        String str = inputTextPassword.getText().substring(0, spaceIndex);
                        inputTextPassword.setText(str);
                        errorPassword.setTextFill(Color.RED);
                        errorPassword.setText(errorSpaceMessage);
                    }
                });
    }

    @FXML
    private void inputTextConfirmPasswordValidate() {
        //empty
        inputTextConfirmPassword.setOnKeyPressed((KeyEvent t) -> {
            String getPassword = inputTextPassword.getText();
            String getConfirmPassword = inputTextConfirmPassword.getText();
            if (t.getCode() == KeyCode.ENTER
                    && inputTextConfirmPassword.getText().isEmpty()) {
                errorConfirmPassword.setTextFill(Color.RED);
                errorConfirmPassword.setText(errorEmptyMessage);
            }
            //empty password
            if (t.getCode() == KeyCode.ENTER
                    && inputTextPassword.getText().isEmpty()) {
                inputTextPassword.requestFocus();
                errorPassword.setTextFill(Color.RED);
                errorPassword.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER && getConfirmPassword.equals(getPassword)) {
                isCompleteForm();
            }
        });
        inputTextConfirmPassword.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
                    IntegerProperty currentLength = new SimpleIntegerProperty();
                    currentLength.bind(Bindings.length(inputTextConfirmPassword.textProperty()));
                    final String space = " ";
                    //empty
                    if (currentLength.getValue() == 0) {
                        errorConfirmPassword.setTextFill(Color.RED);
                        errorConfirmPassword.setText(errorEmptyMessage);
                    } else {
                        errorConfirmPassword.setText(null);
                    }
                    //space
                    if (inputTextConfirmPassword.getText().contains(space)) {
                        int spaceIndex = inputTextConfirmPassword.getText().indexOf(space);
                        String str = inputTextConfirmPassword.getText().substring(0, spaceIndex);
                        inputTextConfirmPassword.setText(str);
                        errorConfirmPassword.setTextFill(Color.RED);
                        errorConfirmPassword.setText(errorSpaceMessage);
                    }
                    //confirm password
                    if (!inputTextPassword.getText().isEmpty()) {
                        String getPassword = inputTextPassword.getText();
                        String getConfirmPassword = inputTextConfirmPassword.getText();
                        if (currentLength.getValue() == 0 && !getConfirmPassword.equals(getPassword)) {
                            errorConfirmPassword.setTextFill(Color.RED);
                            errorConfirmPassword.setText(errorEmptyMessage);
                        } else if (currentLength.getValue() > 0 && !getConfirmPassword.equals(getPassword)) {
                            errorConfirmPassword.setTextFill(Color.RED);
                            errorConfirmPassword.setText(errorConfirmPasswordMessage);
                        } else {
                            errorConfirmPassword.setText(null);
                        }
                    }
                });
    }

    @FXML
    private void inputDobValidate() {
        inputDob.setOnKeyPressed((KeyEvent t) -> {
            if (inputDob.getValue() == null) {
                errorDob.setText(errorEmptyMessage);
            } else {
                errorDob.setText(null);
                isCompleteForm();
            }
        });
    }

    @FXML
    private int inputGenderValidate() {
        int gender = 2;
        if (male.isSelected()) {
            errorGender.setText(null);
            gender = 0;
        } else if (female.isSelected()) {
            errorGender.setText(null);
            gender = 1;
        } else {
            errorGender.setText(errorDefaultGenderMessage);
            other.setSelected(true);
        }
        return gender;
    }

    @FXML
    private void inputTextEmailValidate() {
        //empty
        inputTextEmail.setOnKeyPressed((KeyEvent t) -> {
//            Pattern emailRegex = Pattern.compile(
//                    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*"//local part
//                    + "@"//@
//                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"//domain
//                    ,
//                     Pattern.CASE_INSENSITIVE);
//            Matcher emailCheck = emailRegex.matcher(inputTextEmail.getText());
            if (Validate.isEmptyTextField(t, inputTextEmail)) {
                errorEmail.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER
                    && Validate.checkEmailFormat(inputTextEmail) == 0) {
                isCompleteForm();
            }
        });
        inputTextEmail.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
//                    IntegerProperty currentLength = new SimpleIntegerProperty();
//                    currentLength.bind(Bindings.length(inputTextEmail.textProperty()));
//                    final String space = " ";
//                    Pattern emailRegex = Pattern.compile(
//                            //local part @ domain
//                            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" + "@"
//                            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
//
//                    Matcher emailCheck = emailRegex.matcher(inputTextEmail.getText());
                    if (Validate.isEmptyTextField(inputTextEmail)) {
                        errorEmail.setText(errorEmptyMessage);
                    }
                    //space
                    if (Validate.findWhiteSpace(inputTextEmail)) {
                        errorEmail.setText(errorSpaceMessage);
                        Validate.removeWhiteSpace(inputTextEmail);
                    }
//                    if (inputTextEmail.getText().contains(space)) {
//                        int spaceIndex = inputTextEmail.getText().indexOf(space);
//                        String str = inputTextEmail.getText().substring(0, spaceIndex);
//                        inputTextEmail.setText(str);
//                        errorEmail.setTextFill(Color.RED);
//                        errorEmail.setText(errorSpaceMessage);
//                    }
                    //email check
                    switch (Validate.checkEmailFormat(inputTextEmail)) {
                        case 2:
                            errorEmail.setText(errorEmptyMessage);
                            break;
                        case 1:
                            errorEmail.setText(errorEmailMessage);
                            break;
                        default:
                            errorEmail.setText(null);
                            break;
                    }
//                    if (currentLength.getValue() > 0 && !emailCheck.find()) {
//                        errorEmail.setText(errorEmailMessage);
//                    } else if (currentLength.getValue() == 0 && !emailCheck.find()) {
//                        errorEmail.setText(errorEmptyMessage);
//                    } else {
//                        errorEmail.setText(null);
//                    }
                });
    }

    @FXML
    private void inputTextAddressValidate() {
        //empty
        inputTextAddress.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER
                    && inputTextAddress.getText().isEmpty()) {
                errorAddress.setTextFill(Color.RED);
                errorAddress.setText(errorEmptyMessage);
            }
            //complete
            if (t.getCode() == KeyCode.ENTER && !inputTextAddress.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextAddress.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
                    IntegerProperty currentLength = new SimpleIntegerProperty();
                    currentLength.bind(Bindings.length(inputTextAddress.textProperty()));
                    //empty
                    if (currentLength.getValue() == 0) {
                        errorAddress.setTextFill(Color.RED);
                        errorAddress.setText(errorEmptyMessage);
                    } else {
                        errorAddress.setText(null);
                    }
                });
    }

    @FXML
    private void inputTextPhoneNumberValidate() {
        //empty
        inputTextPhoneNumber.setOnKeyPressed((KeyEvent t2) -> {
            if (t2.getCode() == KeyCode.ENTER
                    && inputTextPhoneNumber.getText().isEmpty()) {
                errorPhoneNumber.setTextFill(Color.RED);
                errorPhoneNumber.setText(errorEmptyMessage);
            }
        });
        inputTextPhoneNumber.textProperty().addListener(
                (ObservableValue<? extends String> ov, String t, String t1) -> {
                    IntegerProperty currentLength = new SimpleIntegerProperty();
                    currentLength.bind(Bindings.length(inputTextPhoneNumber.textProperty()));
                    final String space = " ";
                    //empty
                    if (currentLength.getValue() == 0) {
                        errorPhoneNumber.setTextFill(Color.RED);
                        errorPhoneNumber.setText(errorEmptyMessage);
                    } //length
                    else if (currentLength.getValue() != 10) {
                        errorPhoneNumber.setTextFill(Color.RED);
                        errorPhoneNumber.setText(errorLengthMessage + " (" + currentLength.getValue() + "/10)");
                    } else {
                        errorPhoneNumber.setTextFill(Color.GREEN);
                        errorPhoneNumber.setText(errorLengthMessage + " (" + currentLength.getValue() + "/10)");
                        errorPhoneNumber.setOnMouseClicked((MouseEvent t2) -> {
                            errorPhoneNumber.setText(null);
                        });
                    }
                    //space
                    if (inputTextPhoneNumber.getText().contains(space)) {
                        int spaceIndex = inputTextPhoneNumber.getText().indexOf(space);
                        String str = inputTextPhoneNumber.getText().substring(0, spaceIndex);
                        inputTextPhoneNumber.setText(str);
                        errorPhoneNumber.setTextFill(Color.RED);
                        errorPhoneNumber.setText(errorSpaceMessage);
                    }
                    //letter
                    if (currentLength.getValue() > 0) {
                        char lastLetter = inputTextPhoneNumber.getText().charAt((currentLength.getValue() - 1));
                        if (lastLetter < '0' || lastLetter > '9') {
                            String newInput = inputTextPhoneNumber.getText().substring(0, (currentLength.getValue() - 1));
                            inputTextPhoneNumber.setText(newInput);
                            errorPhoneNumber.setTextFill(Color.RED);
                            errorPhoneNumber.setText(errorLetterMessage);
                        }
                    }
                    //complete
                    inputTextPhoneNumber.setOnKeyPressed((KeyEvent t2) -> {
                        if (t2.getCode() == KeyCode.ENTER && currentLength.getValue() == 10) {
                            isCompleteForm();
                        }
                    });
                });
    }
}
