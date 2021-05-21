package web.dao;

import web.model.User;

import java.util.List;

public interface UserDaoIntr {
    List<User> index();
    User show(int id);
    void save(User user);
    void update(int id, User updatedPerson);
    void delete(int id);
}
