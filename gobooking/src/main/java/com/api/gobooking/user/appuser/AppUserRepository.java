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
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1, appUser.getName());
        query.setParameter(2, appUser.getSurname());
        query.setParameter(3, appUser.getEmail());
        query.setParameter(4, appUser.getBirthDate());
        query.setParameter(5, appUser.getRole());
        query.setParameter(6, appUser.getBalance());
        query.setParameter(7, appUser.getCity());
        query.setParameter(8, appUser.getTaxNumber());
        query.setParameter(9, appUser.getRegistrationDate());
        query.setParameter(10, appUser.getIsBlocked());
        query.setParameter(11, appUser.getIsBannedFromBooking());
        query.setParameter(12, appUser.getIsBannedFromPosting());

        query.executeUpdate();

        return true;
    }

    public Optional<AppUser> findById(Integer id){
        String sql = "SELECT a from app_user a WHERE a.id = ?";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1, id);

        List<AppUser> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<AppUser> findByEmail(String email){
        String sql = "SELECT a from app_user a WHERE a.email = ?";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1, email);

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