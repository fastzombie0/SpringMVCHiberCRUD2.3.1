package web.service;

import web.model.User;

import java.util.List;

public interface UserServiceInter {
    List<User> index();

    User show(int id);

    void save(User user);

    void update(int id, User updatedPerson);

    void delete(int id);
}
