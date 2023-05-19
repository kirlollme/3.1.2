package com.example.spring_boot_hibernate.DAO;

import com.example.spring_boot_hibernate.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public void addUser(User user) {


        entityManager.persist(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
//        String hql = "FROM User";
//        Query query = (Query) entityManager.createQuery( hql );
//        return query.list();
        Query query = (Query) entityManager.createQuery("select a from User a", User.class);
        return query.getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        String hql = "DELETE User WHERE id = :ID";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("ID", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void changeDataUser(Long id, User userAfter) {
        userAfter.setId(id);
        entityManager.merge(userAfter);
//        String hql = "update User "
//                + "SET name = :name "
//                +   ", surname  = :surname "
//                +   ", age      = :age "
//                +  " where id = :id";
//        Query query = (Query) entityManager.createQuery(hql);
//
//        query.setParameter("id"  , id);
//        query.setParameter("name"     , userAfter.getName());
//        query.setParameter("surname" , userAfter.getSurname());
//        query.setParameter("age", userAfter.getAge());
//
//        query.executeUpdate();
    }

    @Override
    public User getById(Long id) {
        String hql = "from User WHERE id = :id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }
}
