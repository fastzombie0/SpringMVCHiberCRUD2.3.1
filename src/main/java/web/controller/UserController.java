package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping()
    public String create(@ModelAttribute("person") User user) {
        userDAO.save(user);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/people";
    }
}
