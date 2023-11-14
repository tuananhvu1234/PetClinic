package Entities;

import DB.main.DB;
import Models.PrescriptionModel;
import java.util.Arrays;
import java.util.List;

public class PrescriptionEntity {

    private final DB<PrescriptionModel> db = new DB<>();

    private String query;
    private List condition;
    private final String columns = " ("
            + "prescription_date"
            + ") ";

    public boolean addPrescription(PrescriptionModel prescription) {
        query = "INSERT INTO Prescription " + columns
                + " VALUES (?)";
        condition = Arrays.asList(
                prescription.getPrescriptionDate()
        );
        return db.setSqlDataRow(query, condition, prescription);
    }

    public PrescriptionModel getOnePrescription(int prescriptionId) {
        query = "SELECT * FROM Prescription WHERE prescription_id = ?";
        condition = Arrays.asList(prescriptionId);
        return db.getOne(query, condition, new PrescriptionModel());
    }

    public List<PrescriptionModel> getAllPrescription() {
        query = "SELECT * FROM Prescription";
        return db.getAll(query, new PrescriptionModel());
    }

    public boolean updatePrescription(PrescriptionModel prescription) {
        query = "UPADTE Prescription SET "
                + "prescription_date = ?"
                + " WHERE prescription_id = ?";
        condition = Arrays.asList(
                prescription.getPrescriptionDate(),
                prescription.getPrescriptionId()
        );
        return db.setSqlDataRow(query, condition, prescription);
    }

    public boolean deletePrescription(int prescriptionId) {
        query = "DELETE FROM Prescription WHERE prescription_id = ?";
        condition = Arrays.asList(prescriptionId);
        return db.setSqlDataRow(query, condition, new PrescriptionModel());
    }
}
