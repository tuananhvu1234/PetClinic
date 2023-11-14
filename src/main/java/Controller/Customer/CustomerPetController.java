/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Customer;

import Entities.PetEntity;
import Entities.UserEntity;
import Models.PetModel;
import Models.UserModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Acer
 */
public class CustomerPetController implements Initializable {

    @FXML
    private TextField petName;

    @FXML
    private TextField petWeight;

    @FXML
    private TextField petAge;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton dog;

    @FXML
    private RadioButton cat;

    private final PetEntity petEntity = new PetEntity();
    private final UserEntity userEntity = new UserEntity();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
    }

    @FXML
    private void add() {
        UserModel user = userEntity.getSessionUser();
        PetModel pet = petEntity.getOnePetUid(user.getUserId());
        String name = petName.getText();
        int age = Integer.valueOf(petAge.getText());
        float weight = Float.valueOf(petWeight.getText());
        int gender = -1;
        String type = "";
        if (male.isSelected()) {
            gender = 0;
        } else if (female.isSelected()) {
            gender = 1;
        }
        if (dog.isSelected()) {
            type = "Dog";
        } else if (cat.isSelected()) {
            type = "Cat";
        }
        if (pet != null) {
            petEntity.updatePet(
                    new PetModel(pet.getPetId(), user.getUserId(), name, type, gender, weight, age));
        } else {
            petEntity.addPet(
                    new PetModel(user.getUserId(), name, type, gender, weight, age));
        }
    }

    @FXML
    private void getData() {
        UserModel user = userEntity.getSessionUser();
        PetModel pet = petEntity.getOnePetUid(user.getUserId());
        if (pet != null) {
            petName.setText(pet.getPetName());
            petAge.setText(String.valueOf(pet.getPetAge()));
            petWeight.setText(String.valueOf(pet.getPetWeight()));
            switch (pet.getPetGender()) {
                case 0:
                    male.setSelected(true);
                    break;
                case 1:
                    female.setSelected(true);
                    break;
                default:
                    male.setSelected(false);
                    female.setSelected(false);
                    break;
            }
            switch (pet.getPetType().toLowerCase()) {
                case "cat":
                    cat.setSelected(true);
                    break;
                case "dog":
                    dog.setSelected(true);
                    break;
                default:
                    cat.setSelected(false);
                    dog.setSelected(false);
                    break;
            }
        } else {
            petName.setText("");
            petAge.setText("");
            petWeight.setText("");
            male.setSelected(false);
            female.setSelected(false);
            cat.setSelected(false);
            dog.setSelected(false);
        }
    }
}
