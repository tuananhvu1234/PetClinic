package Controller;

import com.aptech.mavenproject2.petclinic.App;
import com.aptech.mavenproject2.petclinic.SignMainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Router {

    //sign page
    private static final String SIGN_PAGE = "SPA_Sign_Main";
    private static final String SIGN_IN = "SPA_SignIn";
    private static final String SIGN_UP = "SPA_SignUp";
    //admin
    private static final String ADMIN_PAGE = "SPA_Admin_Main";
    private static final String ADMIN_HOME = "SPA_Admin_Homepage";
    private static final String ADMIN_BOOKING = "SPA_Admin_Booking";
    private static final String ADMIN_BILL = "";
    private static final String ADMIN_SERVICES = "SPA_Admin_Services";
    private static final String ADMIN_MEDICINES = "SPA_Admin_Medicine";
    private static final String ADMIN_MANAGE_ACCOUNT = "SPA_Admin_Manage_Account";
    private static final String ADMIN_YOUR_INFO = "SPA_Admin_Change_Info";
    //doctor
    private static final String DOCTOR_PAGE = "SPA_Doctor_Main";
    private static final String DOCTOR_HOME = "Doctor_Homepage";
    private static final String DOCTOR_BOOKING = "Doctor_Booking";
    private static final String DOCTOR_SCHEDULE = "Doctor_Schedule";
    private static final String DOCTOR_YOUR_INFO = "Doctor_ChangeInfo";
    //client
    private static final String CLIENT_PAGE = "SPA_Customer_Main";
    private static final String CLIENT_HOME = "Customer_Homepage";
    private static final String CLIENT_OUR_DOCTOR = "Customer_Doctor";
    private static final String CLIENT_OUR_SERVICES = "Customer_Services";
    private static final String CLIENT_YOUR_PET = "Customer_pet";
    private static final String CLIENT_BOOKING = "Customer_Booking";
    private static final String CLIENT_YOUR_INFO = "Customer_ChangeInfo";

    //get sign page
    public static String getSignPage() {
        return SIGN_PAGE;
    }

    public static String getSignInPage() {
        return SIGN_IN;
    }

    public static String getSignUpPage() {
        return SIGN_UP;
    }

    //get admin page
    public static String getAdminPage() {
        return ADMIN_PAGE;
    }

    public static String getAdminHomepage() {
        return ADMIN_HOME;
    }

    public static String getAdminBooking() {
        return ADMIN_BOOKING;
    }

    public static String getAdminBill() {
        return ADMIN_BILL;
    }

    public static String getAdminServices() {
        return ADMIN_SERVICES;
    }

    public static String getAdminMedicines() {
        return ADMIN_MEDICINES;
    }

    public static String getAdminManageAccount() {
        return ADMIN_MANAGE_ACCOUNT;
    }

    public static String getAdminInfo() {
        return ADMIN_YOUR_INFO;
    }

    //get doctor page
    public static String getDoctorPage() {
        return DOCTOR_PAGE;
    }

    public static String getDoctorHomepage() {
        return DOCTOR_HOME;
    }

    public static String getDoctorBooking() {
        return DOCTOR_BOOKING;
    }

    public static String getDoctorSchedule() {
        return DOCTOR_SCHEDULE;
    }

    public static String getDoctorInfo() {
        return DOCTOR_YOUR_INFO;
    }

    //get client page
    public static String getClientPage() {
        return CLIENT_PAGE;
    }

    public static String getClientHomepage() {
        return CLIENT_HOME;
    }

    public static String getClientDoctorPage() {
        return CLIENT_OUR_DOCTOR;
    }

    public static String getClientServices() {
        return CLIENT_OUR_SERVICES;
    }

    public static String getClientPet() {
        return CLIENT_YOUR_PET;
    }

    public static String getClientBooking() {
        return CLIENT_BOOKING;
    }

    public static String getClientInfo() {
        return CLIENT_YOUR_INFO;
    }

    //switch sign page
    public static void switchToSignInPage() {
        SignMainController.setIsSwitchSignUpPage(false);
        switchPage(getSignPage());
    }

    public static void switchToSignUpPage() {
        SignMainController.setIsSwitchSignUpPage(true);
        switchPage(getSignPage());
    }

    //switch admin page
    public static void switchToAdminPage() {
        switchPage(getAdminPage());
    }

    //switch doctor page
    public static void switchToDoctorPage() {
        switchPage(getDoctorPage());
    }

    //switch client page
    public static void switchToClientPage() {
        switchPage(getClientPage());
    }

    public static void switchPage(String page) {
        try {
            App.setRoot(page);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
