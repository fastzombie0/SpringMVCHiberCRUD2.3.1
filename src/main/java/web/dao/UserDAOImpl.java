package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDaoIntr {
    @PersistenceContext
    private EntityManager entityManager;
    private static int PEOPLE_COUNT;
    private List<User> people;

    {
        people = new ArrayList<>();
        people.add(new User(++PEOPLE_COUNT, "Andrea", 18));
        people.add(new User(++PEOPLE_COUNT, "Bob", 19));
        people.add(new User(++PEOPLE_COUNT, "Dilan", 20));
        people.add(new User(++PEOPLE_COUNT, "Max", 25));
    }

    @Override
    public List<User> index() {
        return people;
    }

    @Override
    public User show(int id) {
        return people.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(++PEOPLE_COUNT);
        people.add(user);
    }

    public void update(int id, User updatedPerson) {
        User personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
