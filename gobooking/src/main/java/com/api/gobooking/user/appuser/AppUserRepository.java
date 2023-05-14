package com.api.gobooking.user.appuser;


import com.api.gobooking.user.User;
import com.api.gobooking.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;


//import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AppUserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;

    @Transactional
    public boolean save(AppUser appUser){

        String userSql = "INSERT INTO " +
                "\"user\" (name, surname, email, password, birth_date, role) " +
                "VALUES (:name, :surname, :email, :password, :birthdate, :role)";

        Query userQuery = entityManager.createNativeQuery(userSql);

        userQuery.setParameter("name", appUser.getName());
        userQuery.setParameter("surname", appUser.getSurname());
        userQuery.setParameter("email", appUser.getEmail());
        userQuery.setParameter("password", appUser.getPassword());
        userQuery.setParameter("birthdate", appUser.getBirthDate());
        userQuery.setParameter("role", appUser.getRole().toString());

        userQuery.executeUpdate();

        User user = userRepository.findByEmail(appUser.getEmail()).get();
        int userId = user.getId();

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
        String jpql = "SELECT a from AppUser a WHERE a.id = :id";
        TypedQuery<AppUser> query = entityManager.createQuery(jpql, AppUser.class);

        query.setParameter("id", id);

        List<AppUser> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<AppUser> findByEmail(String email){
        String jpql = "SELECT a from User u, AppUser a WHERE u.id = a.id AND u.email = :email";
        TypedQuery<AppUser> query = entityManager.createQuery(jpql, AppUser.class);

        query.setParameter("email", email);

        List<AppUser> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public List<AppUser> findAll() {
        String jpql = "SELECT a FROM AppUser a";
        TypedQuery<AppUser> query = entityManager.createQuery(jpql, AppUser.class);
        return query.getResultList();
    }

    @Transactional
    public void updateAppUser(AppUser appUser){
        userRepository.updateUser(appUser);
    }

    @Transactional
    public void setIsBlocked(Integer id, Boolean isBlocked){
        String sql = "UPDATE app_user " +
                "SET is_blocked = :is_blocked " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("is_blocked", isBlocked);

        query.executeUpdate();
    }

    @Transactional
    public void setIsBannedFromBooking(Integer id, Boolean isBannedFromBooking){
        String sql = "UPDATE app_user " +
                "SET is_banned_from_booking = :is_banned_from_booking " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("is_banned_from_booking", isBannedFromBooking);

        query.executeUpdate();
    }

    @Transactional
    public void setIsBannedFromPosting(Integer id, Boolean isBannedFromPosting){
        String sql = "UPDATE app_user " +
                "SET is_banned_from_posting = :is_banned_from_posting " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("is_banned_from_posting", isBannedFromPosting);

        query.executeUpdate();
    }

    @Transactional
    public void updateBalance(Integer id, Double balance){
        String sql = "UPDATE app_user " +
                "SET balance = :balance " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("balance", balance);

        query.executeUpdate();
    }

    @Transactional
    public void updateCity(Integer id, String city){
        String sql = "UPDATE app_user " +
                "SET city = :city " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("city", city);

        query.executeUpdate();
    }

    @Transactional
    public void updateTaxNumber(Integer id, String taxNumber) {
        String sql = "UPDATE app_user " +
                "SET tax_number = :tax_number " +
                "WHERE id = :id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);
        query.setParameter("tax_number", taxNumber);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}