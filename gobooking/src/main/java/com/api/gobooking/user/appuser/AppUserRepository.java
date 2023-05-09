package com.api.gobooking.user.appuser;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.springframework.data.jpa.repository.Query;
import javax.persistence.Query;

import java.util.List;
import java.util.Optional;

@Repository
public class AppUserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public boolean save(AppUser appUser){

        String userSql = "INSERT INTO " +
                "user (name, surname, email, birth_date, role) " +
                "VALUES (:name, :surname, :email, :birthdate, :role)";

        Query userQuery = entityManager.createNativeQuery(userSql);

        userQuery.setParameter("name", appUser.getName());
        userQuery.setParameter("surname", appUser.getSurname());
        userQuery.setParameter("email", appUser.getEmail());
        userQuery.setParameter("birthdate", appUser.getBirthDate());
        userQuery.setParameter("role", appUser.getRole());

        userQuery.executeUpdate();

        // Get the automatically generated user id
        String lastInsertIdSql = "SELECT LAST_INSERT_ID()";
        Query lastInsertIdQuery = entityManager.createNativeQuery(lastInsertIdSql);
        int userId = ((Number) lastInsertIdQuery.getSingleResult()).intValue();


        String appUserSql = "INSERT INTO " +
                "app_user (id, balance, city, tax_number, registration_date, is_blocked, is_banned_from_booking, is_banned_from_posting) " +
                "VALUES (:id, :balance, :city, :tax_number, :registration_date, :is_blocked, :is_banned_from_booking, :is_banned_from_posting)";

        Query appUserQuery = entityManager.createNativeQuery(appUserSql);

        appUserQuery.setParameter("id", userId);
        appUserQuery.setParameter("balance", appUser.getBalance());
        appUserQuery.setParameter("city", appUser.getCity());
        appUserQuery.setParameter("tax_number", appUser.getTaxNumber());
        appUserQuery.setParameter("registration_date", appUser.getRegistrationDate());
        appUserQuery.setParameter("is_blocked", appUser.getIsBlocked());
        appUserQuery.setParameter("is_banned_from_booking", appUser.getIsBannedFromBooking());
        appUserQuery.setParameter("is_banned_from_posting", appUser.getIsBannedFromPosting());

        appUserQuery.executeUpdate();
        return true;
    }

    public Optional<AppUser> findById(Integer id){
        String sql = "SELECT a from app_user a WHERE a.id = :id";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);

        List<AppUser> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<AppUser> findByEmail(String email){
        String sql = "SELECT a from app_user a WHERE a.email = :email";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("email", email);

        List<AppUser> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public List<AppUser> findAll() {
        String sql = "SELECT * FROM app_user";
        Query query = entityManager.createNativeQuery(sql, AppUser.class);
        return query.getResultList();
    }

    public void updateAppUser(AppUser appUser){
        String updateUserSql = "UPDATE user " +
                "SET name = :name, surname = :surname, password = :password, birthdate = :birthdate " +
                "WHERE user_id = :id";
        Query updateUserQuery = entityManager.createNativeQuery(updateUserSql);
        updateUserQuery.setParameter("name", appUser.getName());
        updateUserQuery.setParameter("surname", appUser.getSurname());
        updateUserQuery.setParameter("password", appUser.getPassword());
        updateUserQuery.setParameter("birthdate", appUser.getBirthDate());
        updateUserQuery.setParameter("id", appUser.getId());

        updateUserQuery.executeUpdate();
    }

    public void setIsBannedFromBooking(Integer id, Boolean isBannedFromBooking){
        String sql = "UPDATE app_user a " +
                "SET a.is_banned_from_booking = ;is_banned_from_booking " +
                "WHERE a.id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("is_banned_from_booking", isBannedFromBooking);

        query.executeUpdate();
    }

    public void setIsBannedFromPosting(Integer id, Boolean isBannedFromPosting){
        String sql = "UPDATE app_user a " +
                "SET a.is_banned_from_posting = ;is_banned_from_posting " +
                "WHERE a.id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("is_banned_from_posting", isBannedFromPosting);

        query.executeUpdate();
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM user WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}