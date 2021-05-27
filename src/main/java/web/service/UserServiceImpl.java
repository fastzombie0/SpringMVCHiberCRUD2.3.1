package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.dao.UserDAOImpl;
import web.dao.UserDaoIntr;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInter {

    private UserDaoIntr userDAO;

    @Autowired
    public UserServiceImpl(UserDaoIntr userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(int id, User updatedPerson) {
        userDAO.update(id, updatedPerson);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
