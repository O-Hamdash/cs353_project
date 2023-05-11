package com.api.gobooking.user;

import com.api.gobooking.user.appuser.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll(){
        String sql = "SELECT * FROM \"user\"";
        Query query = entityManager.createNativeQuery(sql, User.class);
        return query.getResultList();
    }

    public Optional<User> findById(Integer id){
        String jpql = "SELECT u FROM User u WHERE u.id = :id";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("id", id);

        List<User> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<User> findByEmail(String email){
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);

        List<User> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public void updateUser(User user){
        String updateUserSql = "UPDATE user " +
                "SET name = :name, surname = :surname, password = :password, birthdate = :birthdate " +
                "WHERE user_id = :id";
        Query updateUserQuery = entityManager.createNativeQuery(updateUserSql);
        updateUserQuery.setParameter("name", user.getName());
        updateUserQuery.setParameter("surname", user.getSurname());
        updateUserQuery.setParameter("password", user.getPassword());
        updateUserQuery.setParameter("birthdate", user.getBirthDate());
        updateUserQuery.setParameter("id", user.getId());

        updateUserQuery.executeUpdate();
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM user WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
