package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDaoIntr {
    private static int PEOPLE_COUNT;
    private List<User> people;
    {
        people = new ArrayList<>();
        people.add(new User(++PEOPLE_COUNT,"Andrea", 18));
        people.add(new User(++PEOPLE_COUNT,"Bob", 19));
        people.add(new User(++PEOPLE_COUNT,"Dilan", 20));
        people.add(new User(++PEOPLE_COUNT,"Max", 25));
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
