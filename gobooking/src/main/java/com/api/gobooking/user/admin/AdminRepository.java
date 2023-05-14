package com.api.gobooking.user.admin;

import com.api.gobooking.user.UserRepository;
import com.api.gobooking.user.appuser.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;
    public boolean save(Admin admin){
        String userSql = "INSERT INTO " +
                "user (name, surname, email, birth_date, role) " +
                "VALUES (:name, :surname, :email, :birthdate, :role)";

        Query userQuery = entityManager.createNativeQuery(userSql);

        userQuery.setParameter("name", admin.getName());
        userQuery.setParameter("surname", admin.getSurname());
        userQuery.setParameter("email", admin.getEmail());
        userQuery.setParameter("birthdate", admin.getBirthDate());
        userQuery.setParameter("role", admin.getRole());

        userQuery.executeUpdate();

        // Get the automatically generated user id
        String lastInsertIdSql = "SELECT LAST_INSERT_ID()";
        Query lastInsertIdQuery = entityManager.createNativeQuery(lastInsertIdSql);
        int userId = ((Number) lastInsertIdQuery.getSingleResult()).intValue();


        String adminSql = "INSERT INTO " +
                "admin (id, admin_role) " +
                "VALUES (:id, :admin_role)";

        Query adminQuery = entityManager.createNativeQuery(adminSql);

        adminQuery.setParameter("id", userId);
        adminQuery.setParameter("admin_role", admin.getAdminRole());

        adminQuery.executeUpdate();

        return true;
    }

    public Optional<Admin> findById(Integer id){
        String sql = "SELECT a from admin a WHERE a.id = :id";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);

        List<Admin> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<Admin> findByEmail(String email){
        String sql = "SELECT a from admin a WHERE a.email = :email";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("email", email);

        List<Admin> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public List<Admin> findAll() {
        String sql = "SELECT * FROM admin";
        Query query = entityManager.createNativeQuery(sql, Admin.class);
        return query.getResultList();
    }

    public void updateAdmin(Admin admin){
        userRepository.updateUser(admin);

        String updateAdminSql = "UPDATE admin SET admin_role = :admin_role WHERE user_id = :id";
        Query updateAdminQuery = entityManager.createNativeQuery(updateAdminSql);
        updateAdminQuery.setParameter("admin_role", admin.getAdminRole());
        updateAdminQuery.setParameter("id", admin.getId());

        updateAdminQuery.executeUpdate();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}