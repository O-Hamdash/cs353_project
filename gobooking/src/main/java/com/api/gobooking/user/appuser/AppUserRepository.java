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

        String sql = "INSERT INTO " +
                "app_user (name, surname, email, birth_date, role, balance, city, tax_number, registration_date, is_blocked, is_banned_from_booking, is_banned_from_posting) " +
                "VALUES (:name, :surname, :email, :birthdate, :role, :balance, :city, :tax_number, :registration_date, :is_blocked, :is_banned_from_booking, :is_banned_from_posting)";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("name", appUser.getName());
        query.setParameter("surname", appUser.getSurname());
        query.setParameter("email", appUser.getEmail());
        query.setParameter("birthdate", appUser.getBirthDate());
        query.setParameter("role", appUser.getRole());
        query.setParameter("balance", appUser.getBalance());
        query.setParameter("city", appUser.getCity());
        query.setParameter("tax_number", appUser.getTaxNumber());
        query.setParameter("registration_date", appUser.getRegistrationDate());
        query.setParameter("is_blocked", appUser.getIsBlocked());
        query.setParameter("is_banned_from_booking", appUser.getIsBannedFromBooking());
        query.setParameter("is_banned_from_posting", appUser.getIsBannedFromPosting());

        query.executeUpdate();

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
        String sql = "UPDATE app_user a " +
                "SET a.name = :name ," +
                " a.surname = :surname ," +
                " a.password = :password," +
                " a.birth_date = :birthdate" +
                "WHERE a.id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", appUser.getId());
        query.setParameter("name", appUser.getName());
        query.setParameter("surname", appUser.getSurname());
        query.setParameter("password", appUser.getPassword());
        query.setParameter("birthdate", appUser.getBirthDate());

        query.executeUpdate();
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM app_user WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}