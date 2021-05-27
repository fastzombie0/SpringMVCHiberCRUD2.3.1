package web.dao;

import web.model.User;

import java.util.List;

public interface UserDaoIntr {
    List<User> getAllUser();
    User findById(int id);
    void save(User user);
    void update(int id, User updatedPerson);
    void delete(int id);
}
