package Controller.SignController;

import Controller.Router;
import Entities.*;
import Session.SessionWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SignInController implements Initializable {

    @FXML
    private TextField inputTextUsername;

    @FXML
    private PasswordField inputTextPassword;

    @FXML
    private CheckBox inputCBoxRemember;

    @FXML
    private Label errorUsername;

    @FXML
    private Label errorPassword;

    private final AccountEntity accM = new AccountEntity();
    private final UserEntity userEntity = new UserEntity();
    private final SignEntity signM = new SignEntity();
    private final SessionWriter sessionWriter = new SessionWriter();

    private final String errorEmptyMessage = "Required";
    private final String errorSpaceMessage = "Space Format";
    private final String incorrectMessage = "Username or Password is incorrect !!";
    private final String reEnterMessage = "Please re-enter username and password";
    private final String SqlInjectionMessage = "SQL Injection detection !!!!";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //remember
        String sessionUsername = "";
        String sessionPassword = "";
        if (sessionWriter.isLineOfSession("remember=true")) {
            String username = "username=";
            sessionUsername = sessionWriter.getLineStartWith(username).substring(username.length());
            String password = "password=";
            sessionPassword = sessionWriter.getLineStartWith(password).substring(password.length());
            inputCBoxRemember.setSelected(true);
        }
        inputTextUsername.setText(sessionUsername);
        inputTextPassword.setText(sessionPassword);
        //validation
        inputTextUsernameValidate();
        inputTextPasswordValidate();
    }

    //link
    @FXML
    public void goToSignUp() {
        Router.switchToSignUpPage();
    }

    @FXML
    private void goToHomePage() {
        int role = userEntity.getSessionUser().getRole();
        if (role == 1) {
            Router.switchToAdminPage();
        }
        if (role == 2) {
            Router.switchToDoctorPage();
        }
        if (role == 3) {
            Router.switchToClientPage();
        }
    }

    //sign in
    @FXML
    public void isCompleteForm() {
        if (inputTextPassword.getText() == null
                || inputTextPassword.getText().isEmpty()) {

            inputTextPassword.requestFocus();

        } else if (inputTextUsername.getText() == null
                || inputTextUsername.getText().isEmpty()) {

            inputTextUsername.requestFocus();
            errorUsername.setText(errorEmptyMessage);

        } else {
            SignInEvent();
        }
    }

    @FXML
    private void SignInEvent() {
        String inputUsername = inputTextUsername.getText();
        String inputPassword = inputTextPassword.getText();
        boolean remember = false;
        if (accM.checkInputAccount(inputUsername, inputPassword)) {
            if (inputCBoxRemember.isSelected()) {
                remember = true;
            }
            if (signM.signIn(inputUsername, inputPassword, remember)) {
                goToHomePage();
            } else {
                errorInput();
            }
        } else {
            errorUsername.setText(SqlInjectionMessage);
            errorPassword.setText(SqlInjectionMessage);
            errorExitApp();
        }
    }

    //validation
    private void inputTextUsernameValidate() {
        inputTextUsername.setOnKeyPressed((KeyEvent t) -> {
            //empty
            if (t.getCode() == KeyCode.ENTER
                    && inputTextUsername.getText().isEmpty()) {
                errorUsername.setText(errorEmptyMessage);
            }
            if (t.getCode() == KeyCode.ENTER
                    && !inputTextUsername.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextUsername.textProperty().addListener((ov, t, t1) -> {
            IntegerProperty currentLength = new SimpleIntegerProperty();
            currentLength.bind(Bindings.length(inputTextUsername.textProperty()));
            String space = " ";
            //empty
            if (currentLength.getValue() == 0) {
                errorUsername.setText(errorEmptyMessage);
            } else {
                errorUsername.setText(null);
            }
            //space
            if (inputTextUsername.getText().contains(space)) {
                int spaceIndex = inputTextUsername.getText().indexOf(space);
                String str = inputTextUsername.getText().substring(0, spaceIndex);
                inputTextUsername.setText(str);
                errorUsername.setText(errorSpaceMessage);
            }
        });
    }

    private void inputTextPasswordValidate() {
        //empty
        inputTextPassword.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER
                    && inputTextPassword.getText().isEmpty()) {
                errorPassword.setText(errorEmptyMessage);
            }
            if (!inputTextPassword.getText().isEmpty()) {
                errorPassword.setText(null);
            }
            if (t.getCode() == KeyCode.ENTER
                    && !inputTextPassword.getText().isEmpty()) {
                isCompleteForm();
            }
        });
        inputTextPassword.textProperty().addListener((ov, t, t1) -> {
            IntegerProperty currentLength = new SimpleIntegerProperty();
            currentLength.bind(Bindings.length(inputTextPassword.textProperty()));
            String space = " ";
            //empty
            if (currentLength.getValue() == 0) {
                errorPassword.setText(errorEmptyMessage);
            } else {
                errorPassword.setText(null);
            }
            //space
            if (inputTextPassword.getText().contains(space)) {
                int spaceIndex = inputTextPassword.getText().indexOf(space);
                String str = inputTextPassword.getText().substring(0, spaceIndex);
                inputTextPassword.setText(str);
                errorPassword.setText(errorSpaceMessage);
            }
        });
    }

    //error
    private void errorInput() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(false);
        alert.setTitle("Login Fail !!");
        alert.setHeaderText(incorrectMessage);
        alert.setContentText(reEnterMessage);
        alert.show();
    }

    private void errorExitApp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(false);
        alert.setTitle("ERROR");
        alert.setHeaderText(SqlInjectionMessage);
        alert.setOnCloseRequest((DialogEvent t) -> {
            Platform.exit();
        });
        ButtonType exitApp = new ButtonType("Exit");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(exitApp);
        if (alert.showAndWait().get() == exitApp) {
            Platform.exit();
        }
    }
}
