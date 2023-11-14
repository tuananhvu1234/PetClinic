package Entities;

import Models.UserModel;
import Session.SessionWriter;
import java.util.ArrayList;
import java.util.List;

public class SignEntity {

    private final AccountEntity accModel = new AccountEntity();
    private final UserEntity userModel = new UserEntity();
    private final SessionWriter sessionWr = new SessionWriter();

    public boolean signUp(UserModel user) {
        return accModel.activeAccount(user);
    }

    public boolean signIn(String username, String password, boolean remember) {
        if (checkSignIn(username, password)) {
            int userId = accModel.getAccountId(username);
            List<String> listLineSignIn = new ArrayList<>();
            listLineSignIn.add("userId=" + userId);
            listLineSignIn.add("remember=" + remember);
            if (remember == true) {
                listLineSignIn.add("username=" + username);
                listLineSignIn.add("password=" + password);
            }
            if (accModel.getOneAccount(username).getStatus() == 0) {
                return false;
            }
            if (sessionWr.setSession(listLineSignIn)) {
                return true;
            }
        }
        return false;
    }

    public boolean signOut(int userId, boolean remember) {
        UserModel user = userModel.getOneUser(userId);
        List<String> listLineSignOut = new ArrayList<>();
        listLineSignOut.add("remember=" + remember);
        if (remember == true) {
            listLineSignOut.add("username=" + user.getAccount().getUsername());
            listLineSignOut.add("password=" + user.getAccount().getPassword());
        }
        return sessionWr.setSession(listLineSignOut);
    }

    private boolean checkSignIn(String username, String password) {
        String checkPass = null;
        if (accModel.isExistAccount(username)) {
            checkPass = accModel.getOneAccount(username).getPassword();
        }
        return password.equals(checkPass);
    }
}
