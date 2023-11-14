package com.aptech.mavenproject2.petclinic;

import Controller.Router;
import Controller.SignController.SignOutController;
import Entities.UserEntity;
import Session.SessionWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DoctorMainController implements Initializable {

    @FXML
    private BorderPane mainPage;

    @FXML
    private Label labelTitlePage;

    @FXML
    private Button btnHome, btnBooking, btnSchedule, btnInfo;

    private static final String HOME_TITLE = "Welcome to Pet Clinic";
    private static final String BOOKING_TITLE = "Booking";
    private static final String SCHEDULE_TITLE = "Schedule";
    private static final String YOUR_INFO_TITLE = "Your Info";

    private final UserEntity userEntity = new UserEntity();
    private final SessionWriter session = new SessionWriter();
    private final int id = userEntity.getSessionUser().getUserId();
    private final String bool = session.getLineStartWith("remember=").substring("remember=".length());
    private final boolean remember = Boolean.parseBoolean(bool);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getHome();
    }

    private void setPageTitle(String title) {
        labelTitlePage.setText(title);
    }

    @FXML
    private void getHome() {
        try {
            Pane view = App.getPane(Router.getDoctorHomepage());

            mainPage.setCenter(view);
            btnHome.setStyle("-fx-background-color : #947E72;-fx-text-fill : #FFF;");
            btnBooking.setStyle("");
            btnSchedule.setStyle("");
            btnInfo.setStyle("");

            setPageTitle(HOME_TITLE);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void getBooking() {
        try {
            Pane view = App.getPane(Router.getDoctorBooking());

            btnHome.setStyle("");
            btnBooking.setStyle("-fx-background-color : #947E72;-fx-text-fill : #FFF;");
            btnSchedule.setStyle("");
            btnInfo.setStyle("");

            mainPage.setCenter(view);
            setPageTitle(BOOKING_TITLE);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void getSchedule() {
        try {
            Pane view = App.getPane(Router.getDoctorSchedule());

            btnHome.setStyle("");
            btnBooking.setStyle("");
            btnSchedule.setStyle("-fx-background-color : #947E72;-fx-text-fill : #FFF;");
            btnInfo.setStyle("");

            mainPage.setCenter(view);
            setPageTitle(SCHEDULE_TITLE);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void getUserInfo() {
        try {
            Pane view = App.getPane(Router.getDoctorInfo());

            btnHome.setStyle("");
            btnBooking.setStyle("");
            btnSchedule.setStyle("");
            btnInfo.setStyle("-fx-background-color : #947E72;-fx-text-fill : #FFF;");

            mainPage.setCenter(view);
            setPageTitle(YOUR_INFO_TITLE);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void SignOut() {
        SignOutController.signOutEvent(id, remember);
    }
}
