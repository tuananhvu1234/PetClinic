package Entities;

import DB.main.DB;
import Models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountEntity {

    private final DB<UserModel> db = new DB<>();
    private final UserEntity userEntity = new UserEntity();

    private String query;
    private List condition;

    public AccountModel getOneAccount(String username) {
        query = "SELECT username, password, status"
                + " FROM User WHERE username = ?";
        condition = Arrays.asList(username);
        return db.getOne(query, condition, new AccountModel());
    }

    public AccountModel getOneAccount(int userId) {
        return userEntity.getOneUser(userId).getAccount();
    }

    public List<AccountModel> getAllAccount() {
        List<AccountModel> listAcc = new ArrayList<>();
        userEntity.getAllUser().forEach(acc -> {
            listAcc.add(acc.getAccount());
        });
        return listAcc;
    }

    public boolean updateAccount(String username, String password) {
        int userId = userEntity.getSessionUser().getUserId();
        query = "UPDATE User"
                + " SET username = ?, password = ?"
                + " WHERE user_id = ?";
        condition = Arrays.asList(username, password, userId);
        return db.setSqlDataRow(query, condition, new UserModel());
    }

    public boolean updateAccount(String username, String password, int status, int userId) {
        query = "UPDATE User"
                + " SET username = ?, password = ?, status = ?"
                + " WHERE user_id = ?";
        condition = Arrays.asList(username, password, status, userId);
        return db.setSqlDataRow(query, condition, new UserModel());
    }

    public boolean deleteAccount(int userId) {
        query = "UPDATE User SET status = 0 WHERE user_id = ?";
        condition = Arrays.asList(userId);
        return db.setSqlDataRow(query, condition, new UserModel());
    }

    public int getAccountId(String username) {
        query = "SELECT *"
                + " FROM User WHERE username = ?";
        condition = Arrays.asList(username);
        return db.getOne(query, condition, new UserModel()).getUserId();
    }

    public String starPassword(int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += "*";
        }
        return s;
    }

    public boolean isExistAccount(String username) {
        return getOneAccount(username) != null;
    }

    public boolean activeAccount(UserModel user) {
        if (userEntity.isOldUser(user.getEmail())) {
            int id = getAccountId(user.getAccount().getUsername());
            return updateAccount(user.getAccount().getUsername(), user.getAccount().getPassword(), 1, id);
        }
        if (isExistAccount(user.getAccount().getUsername())) {
            return false;
        }
        return userEntity.addClientUser(user);
    }

    public boolean checkInputAccount(String username, String password) {
        String checkUn = "";
        String checkPw = "";
        for (String str : username.split(" ")) {
            checkUn += str;
        }
        for (String str : password.split(" ")) {
            checkPw += str;
        }
        String regex = "[\'|\"][\'|\"][a-zA-Z0-9]+[=][a-zA-Z0-9]+[-]+";
        if (!checkUn.matches(regex) && !checkPw.matches(regex)) {
            return true;
        }
        return false;
    }
}
