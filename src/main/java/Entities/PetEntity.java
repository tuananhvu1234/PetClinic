package Entities;

import DB.main.DB;
import Models.PetModel;
import java.util.Arrays;
import java.util.List;

public class PetEntity {

    private final DB<PetModel> db = new DB<>();

    private String query;
    private List condition;
    private final String columns = " ("
            + "user_id,"
            + "pet_type,"
            + "pet_name,"
            + "pet_gender,"
            + "pet_weight,"
            + "pet_age"
            + ") ";

    public boolean addPet(PetModel pet) {
        query = "INSERT INTO Pet " + columns
                + " VALUES (?,?,?,?,?,?)";
        condition = Arrays.asList(
                pet.getUserId(),
                pet.getPetType(),
                pet.getPetName(),
                pet.getPetGender(),
                pet.getPetWeight(),
                pet.getPetAge()
        );
        return db.setSqlDataRow(query, condition, pet);
    }

    public PetModel getOnePet(int petId) {
        query = "SELECT * FROM Pet WHERE pet_id = ?";
        condition = Arrays.asList(petId);
        return db.getOne(query, condition, new PetModel());
    }

    public PetModel getOnePetUid(int userId) {
        query = "SELECT p.*, u.* "
                + "FROM pet_clinic.pet AS p "
                + "INNER JOIN pet_clinic.user AS u "
                + "ON p.user_id = u.user_id "
                + "WHERE u.user_id = ?";
        condition = Arrays.asList(userId);
        return db.getOne(query, condition, new PetModel());
    }

    public List<PetModel> getAll() {
        query = "SELECT * FORM Pet";
        return db.getAll(query, new PetModel());
    }

    public boolean updatePet(PetModel pet) {
        query = "UPDATE Pet SET "
                + "pet_name = ?,"
                + "pet_weight = ?,"
                + "pet_age = ?"
                + " WHERE pet_id = ?";
        condition = Arrays.asList(
                pet.getPetName(),
                pet.getPetWeight(),
                pet.getPetAge()
        );
        return db.setSqlDataRow(query, condition, pet);
    }

    public boolean deletePet(int petId) {
        query = "DELETE FROM Pet WHERE pet_id = ?";
        condition = Arrays.asList(petId);
        return db.setSqlDataRow(query, condition, new PetModel());
    }
}
