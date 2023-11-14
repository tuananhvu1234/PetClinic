package Models;

import DB.common.DBCommon;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingModel extends DBCommon<BookingModel> {

    private int bookingId;
    private int serviceId;
    private int userId;
    private int petId;
    private Date bookingDate;
    private String bookingTime;
    private Date updateDate;
    private float price;

    public BookingModel() {
    }

    public BookingModel(int serviceId, int userId, int petId, Date bookingDate, String bookingTime, Date updateDate, float price) {
        this.serviceId = serviceId;
        this.userId = userId;
        this.petId = petId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.updateDate = updateDate;
        this.price = price;
    }

    public BookingModel(int bookingId, int serviceId, int userId, int petId, Date bookingDate, String bookingTime, Date updateDate, float price) {
        this.bookingId = bookingId;
        this.serviceId = serviceId;
        this.userId = userId;
        this.petId = petId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.updateDate = updateDate;
        this.price = price;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookingModel{"
                + "bookingId=" + bookingId
                + ", serviceId=" + serviceId
                + ", userId=" + userId
                + ", petId=" + petId
                + ", bookingDate=" + bookingDate
                + ", bookingTime=" + bookingTime
                + ", updateDate=" + updateDate
                + ", price=" + price
                + '}';
    }

    @Override
    public BookingModel setResultSetValue(BookingModel object, ResultSet rs)
            throws SQLException {
        object.setBookingId(rs.getInt("booking_id"));
        object.setServiceId(rs.getInt("service_id"));
        object.setUserId(rs.getInt("user_id"));
        object.setPetId(rs.getInt("pet_id"));
        object.setBookingDate(rs.getDate("booking_date"));
        object.setBookingTime(rs.getString("booking_time"));
        object.setUpdateDate(rs.getDate("update_date"));
        object.setPrice(rs.getFloat("price"));
        return object;
    }

}
