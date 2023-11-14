package Controller.SignController;

import Controller.Router;
import Entities.SignEntity;
import javafx.fxml.FXML;

public class SignOutController {

    private static final SignEntity signEntity = new SignEntity();

    @FXML
    public static void signOutEvent(int id, boolean remember) {
        if (signEntity.signOut(id, remember)) {
            Router.switchToSignInPage();
        }
    }
}
