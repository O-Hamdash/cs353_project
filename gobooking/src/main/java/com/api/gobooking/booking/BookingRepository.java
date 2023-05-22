package com.api.gobooking.booking;

import com.api.gobooking.user.UserRepository;
import com.api.gobooking.user.appuser.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BookingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public boolean insert(Booking booking){

        String bookingSql = "INSERT INTO " +
                "booking (booking_id , start_date, end_date, status,property_id,booker_id) " +
                "VALUES (:booking_id, :start_date, :end_date, :status,:property_id,:booker_id)";

        Query bookingQuery = entityManager.createNativeQuery(bookingSql);

        bookingQuery.setParameter("booking_id", booking.getBooking_id());
        bookingQuery.setParameter("start_date", booking.getStart_date());
        bookingQuery.setParameter("end_date", booking.getEnd_date());
        bookingQuery.setParameter("status", booking.getStatus());
        bookingQuery.setParameter("property_id", booking.getProperty_id());
        bookingQuery.setParameter("booker_id", booking.getBooker_id());

        int rowsAffected = bookingQuery.executeUpdate();

        return rowsAffected > 0;
    }

    @Transactional
    public boolean update(Booking booking) {
        String updateSql = "UPDATE booking " +
                "SET start_date = :start_date, end_date = :end_date, status = :status " +
                "WHERE booking_id = :id";

        Query updateQuery = entityManager.createNativeQuery(updateSql);

        updateQuery.setParameter("start_date", booking.getStart_date());
        updateQuery.setParameter("end_date", booking.getEnd_date());
        updateQuery.setParameter("status", booking.getStatus());
        updateQuery.setParameter("id", booking.getBooking_id());

        int rowsAffected = updateQuery.executeUpdate();

        return rowsAffected > 0;
    }

    @Transactional
    public boolean deleteById(int id) {
        String deleteSql = "DELETE FROM booking WHERE booking_id = :id";

        Query deleteQuery = entityManager.createNativeQuery(deleteSql);

        deleteQuery.setParameter("id", id);

        int rowsAffected = deleteQuery.executeUpdate();

        return rowsAffected > 0;
    }

    public Booking findById(int id) {
        String selectSql = "SELECT * FROM booking WHERE booking_id = :id";

        Query selectQuery = entityManager.createNativeQuery(selectSql, Booking.class);

        selectQuery.setParameter("id", id);

        try {
            return (Booking) selectQuery.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Booking> findAllByBookerId(int booker_id) {
        String selectSql = "SELECT * FROM booking WHERE booker_id = :id";

        Query selectQuery = entityManager.createNativeQuery(selectSql, Booking.class);

        selectQuery.setParameter("id", booker_id);

        return selectQuery.getResultList();
    }

    public List<Booking> findAllByPropertyId(int property_id) {
        String selectSql = "SELECT * FROM booking WHERE property_id = :id";

        Query selectQuery = entityManager.createNativeQuery(selectSql, Booking.class);

        selectQuery.setParameter("id", property_id);

        return selectQuery.getResultList();
    }

    public List<Booking> findAll() {
        String selectSql = "SELECT * FROM booking";

        Query selectQuery = entityManager.createNativeQuery(selectSql, Booking.class);

        return selectQuery.getResultList();
    }
}
