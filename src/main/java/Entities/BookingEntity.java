package Entities;

import DB.main.DB;
import Models.BookingModel;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingEntity {

    private final DB<BookingModel> db = new DB<>();
    private final Date today = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

    private String query;
    private List condition;
    private final String columns = "("
            + "service_id,"
            + "user_id,"
            + "pet_id,"
            + "booking_date,"
            + "booking_time,"
            + "update_date,"
            + "price"
            + ")";

    public boolean addBooking(BookingModel booking) {
        query = "INSERT INTO Booking " + columns
                + " VALUES (?,?,?,?,?,?,?)";
        condition = Arrays.asList(
                booking.getServiceId(),
                booking.getUserId(),
                booking.getPetId(),
                booking.getBookingDate(),
                booking.getBookingTime(),
                booking.getUpdateDate(),
                booking.getPrice()
        );
        return db.setSqlDataRow(query, condition, booking);
    }

    public BookingModel getOneBooking(int bookingId) {
        query = "SELECT * FROM Booking WHERE booking_id = ?";
        condition = Arrays.asList(bookingId);
        return db.getOne(query, condition, new BookingModel());
    }

    public ArrayList<BookingModel> getAllBooking() {
        query = "SELECT * FROM Booking";
        return db.getAll(query, new BookingModel());
    }

    public ArrayList<BookingModel> getAllBookingInDay() {
        return getAllBooking("", today, "");
    }

    public ArrayList<BookingModel> getAllBooking(String user, Date date, String time) {
        List list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        query = "SELECT b.* FROM Booking AS b"
                + " INNER JOIN User AS u"
                + " ON b.user_id = u.user_id";
        if (!user.isEmpty()) {
            list2.add(" u.full_name LIKE ? ");
            list.add("%" + user + "%");
        }
        if (date != null) {
            list2.add(" b.booking_date LIKE ? ");
            list.add("%" + date + "%");
        }
        if (!time.isEmpty()) {
            list2.add(" b.booking_time LIKE ? ");
            list.add("%" + time + "%");
        }
        if (!list2.isEmpty()) {
            query += " WHERE ";
            for (int i = 0; i < list2.size(); i++) {
                query += list2.get(i);
                if (i < list2.size() - 1) {
                    query += " AND ";
                }
            }
        }
        return db.getAll(query, list, new BookingModel());
    }

    public ArrayList<BookingModel> getAllWithCustomerName(String cusName) {
        query = "SELECT * "
                + "FROM booking "
                + "JOIN user ON booking.user_id = user.user_id "
                + "WHERE user.full_name like ?";

        condition = Arrays.asList(
                "%" + cusName + "%"
        );
        
        return db.getAll(query, condition, new BookingModel());
    }

    public boolean updateBooking(BookingModel booking) {
        query = "UPDATE Booking SET "
                + "booking_date = ?,"
                + "booking_time = ?,"
                + "update_date = ?"
                + " WHERE booking_id = ?";
        condition = Arrays.asList(
                booking.getBookingDate(),
                booking.getBookingTime(),
                booking.getUpdateDate(),
                booking.getBookingId()
        );
        return db.setSqlDataRow(query, condition, booking);
    }

    public boolean deleteBooking(int bookingId) {
        query = "DELETE FROM Booking WHERE booking_id = ?";
        condition = Arrays.asList(bookingId);
        return db.setSqlDataRow(query, condition, new BookingModel());
    }
}
