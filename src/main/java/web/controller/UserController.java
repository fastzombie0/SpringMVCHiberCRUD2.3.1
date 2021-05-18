package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.UserDAOImpl;
import web.model.User;

@Controller
@RequestMapping("/people")
public class UserController {
    private final UserDAOImpl userDAO;

    @Autowired
    public UserController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) { //получение всех юзеров из дао и передача на представление
        model.addAttribute("people", userDAO.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { //получение одного по id из дао
        model.addAttribute("person", userDAO.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());
        return "people/new";
    }

    @GetMapping()
    public String create(@ModelAttribute("person") User user) {
        userDAO.save(user);
        return "redirect:/people";
    }
}
