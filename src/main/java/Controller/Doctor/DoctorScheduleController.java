package Controller.Doctor;

import Models.*;
import Entities.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DoctorScheduleController implements Initializable {

    UserEntity user = new UserEntity();

    @FXML
    private DatePicker doctorScheduleDate;

    @FXML
    private RadioButton morning;

    @FXML
    private RadioButton afternoon;

    @FXML
    private RadioButton all;

    @FXML
    private DatePicker txtSearch;

    @FXML
    private TextField txtScheduleId;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<DoctorSchedualModel> tableDoctorSchedule;

    @FXML
    private TableColumn<DoctorSchedualModel, String> scheduleID;

    @FXML
    private TableColumn<DoctorSchedualModel, String> scheduleDate;

    @FXML
    private TableColumn<DoctorSchedualModel, String> scheduleTime;

    private final DoctorEntity de = new DoctorEntity();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        validated();
    }

    @FXML
    private void initData() {
//        UserModel sessionUser = user.getSessionUser();

        ArrayList<UserModel> um = de.getAllSchedual();
        ObservableList<DoctorSchedualModel> dsms = FXCollections.observableArrayList();

        for (UserModel userLocal : um) {
            DoctorSchedualModel doctor = (DoctorSchedualModel) userLocal;
            dsms.add(doctor);
        }

        table(dsms);
    }

    @FXML
    private void search() {
//        UserModel sessionUser = user.getSessionUser();
//        String dateSearch = txtSearch.getValue().toString();
//        
//        DoctorEntity de = new DoctorEntity();
//        ArrayList<UserModel> um =  de.getAllSchedualOf(sessionUser.getUserId());
//        ObservableList<DoctorSchedualModel> dsms = FXCollections.observableArrayList();
//        for (UserModel user : um){
//            DoctorSchedualModel doctor = (DoctorSchedualModel)user;
//            dsms.add(doctor);
//        }
//        
//        table(dsms);
    }

    @FXML
    private void table(ObservableList<DoctorSchedualModel> dsms) {
        tableDoctorSchedule.setItems(dsms);
        scheduleID.setCellValueFactory(f -> {
            StringProperty scheduleId = new SimpleStringProperty();

            int id = f.getValue().getDoctorSchedualId();
            scheduleId.setValue(String.valueOf(id));
            return scheduleId;
        });
        scheduleDate.setCellValueFactory(f -> {
            StringProperty ScheduleDate = new SimpleStringProperty();

            ScheduleDate.setValue(f.getValue().getDoctorSchedualDate().toString());
            return ScheduleDate;
        });
        scheduleTime.setCellValueFactory(f -> {
            StringProperty ScheduleTime = new SimpleStringProperty();

            ScheduleTime.setValue(f.getValue().getDoctorSchedualTime());
            return ScheduleTime;
        });

        tableDoctorSchedule.setRowFactory(tv -> {
            TableRow<DoctorSchedualModel> myRow = new TableRow<>();

            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = tableDoctorSchedule.getSelectionModel().getSelectedIndex();

                    int scheduleId = tableDoctorSchedule.getItems().get(myIndex).getDoctorSchedualId();
                    Date date = tableDoctorSchedule.getItems().get(myIndex).getDoctorSchedualDate();
                    String time = tableDoctorSchedule.getItems().get(myIndex).getDoctorSchedualTime();

                    txtScheduleId.setText(String.valueOf(scheduleId));
                    doctorScheduleDate.setValue(date.toLocalDate());

                    if (time.equals("Morning")) {
                        morning.setSelected(true);
                    } else if (time.equals("Afternoon")) {
                        afternoon.setSelected(true);
                    } else {
                        all.setSelected(true);
                    }
                }
            });

            return myRow;
        });
    }

    @FXML
    private void validated() {
        boolean flag = false;
        String id = txtScheduleId.getText();
        LocalDate date = this.doctorScheduleDate.getValue();
        boolean Morning = this.morning.isSelected();
        boolean Afternoon = this.afternoon.isSelected();
        boolean All = this.all.isSelected();

        if (date == null) {
            flag = true;
        } else {

        }

        if (Morning || Afternoon || All) {

        } else {
            flag = true;
        }

        btnSave.setDisable(flag);
    }
}
