package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDaoIntr {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return  query.getSingleResult();
        //return people.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public void update(int id, User updatedPerson) {
        entityManager.merge(updatedPerson);
    }
    @Transactional
    public void delete(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();
        entityManager.remove(user);
    }
}
