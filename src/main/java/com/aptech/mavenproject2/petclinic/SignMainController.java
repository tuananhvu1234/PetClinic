package com.aptech.mavenproject2.petclinic;

import Controller.Router;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SignMainController implements Initializable {

    @FXML
    private BorderPane mainPage;

    private static boolean isSwitchSignUpPage = false;

    public static void setIsSwitchSignUpPage(boolean isSwitchSignUpPage) {
        SignMainController.isSwitchSignUpPage = isSwitchSignUpPage;
    }

    private boolean isSwitchPage() {
        return isSwitchSignUpPage;
    }

    @FXML
    private void getSignIn() {
        try {
            Pane view = App.getPane(Router.getSignInPage());
            mainPage.setCenter(view);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void getSignUp() {
        try {
            Pane view = App.getPane(Router.getSignUpPage());
            mainPage.setCenter(view);
        } catch (IOException ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (isSwitchPage() == true) {
            getSignUp();
        } else {
            getSignIn();
        }
    }
}
