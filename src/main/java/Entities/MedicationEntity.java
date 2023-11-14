package Entities;

import DB.main.DB;
import Models.MedicationModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicationEntity {

    private final DB<MedicationModel> db = new DB<>();

    private String query;
    private List condition;
    private final String columns = "("
            + "medication_name,"
            + "description,"
            + "price"
            + ")";

    public boolean addMedication(MedicationModel medication) {
        query = "INSERT INTO Medication " + columns
                + " VALUES (?,?,?)";
        condition = Arrays.asList(
                medication.getMedicationName(),
                medication.getDescription(),
                medication.getPrice()
        );
        return db.setSqlDataRow(query, condition, medication);
    }

    public MedicationModel getOneMedication(int medicationId) {
        query = "SELECT * FROM Medication WHERE medication_id = ?";
        condition = Arrays.asList(medicationId);
        return db.getOne(query, condition, new MedicationModel());
    }

    public ArrayList<MedicationModel> getAllMedication() {
        query = "SELECT * FROM Medication";
        return db.getAll(query, new MedicationModel());
    }

    public ArrayList<MedicationModel> getAllMedication(String name) {
        query = "SELECT * FROM Medication WHERE medication_name LIKE ?";
        condition = Arrays.asList("%" + name + "%");
        return db.getAll(query, condition, new MedicationModel());
    }

    public boolean updateMedication(MedicationModel medication) {
        query = "UPDATE Medication SET "
                + "medication_name = ?,"
                + "description = ?,"
                + "price = ?"
                + " WHERE medication_id = ?";
        condition = Arrays.asList(
                medication.getMedicationName(),
                medication.getDescription(),
                medication.getPrice(),
                medication.getMedicationId()
        );
        return db.setSqlDataRow(query, condition, medication);
    }

    public boolean deleteMedication(int medicationId) {
        query = "DELETE FROM Medication WHERE medication_id = ?";
        condition = Arrays.asList(medicationId);
        return db.setSqlDataRow(query, condition, new MedicationModel());
    }
}
