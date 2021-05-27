package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceInter;

@Controller
@RequestMapping("/people")
public class UserController {

    private final UserServiceInter userServiceInter;

    @Autowired
    public UserController(UserServiceInter userServiceInter) {
        this.userServiceInter = userServiceInter;
    }

    @GetMapping()
    public String getAllUser(Model model) { //получение всех юзеров из дао и передача на представление
        model.addAttribute("people", userServiceInter.getAllUser());
        return "people/getAllUser";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) { //получение одного по id из дао
        model.addAttribute("person", userServiceInter.findById(id));
        return "people/findById";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User user) {
        userServiceInter.save(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userServiceInter.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userServiceInter.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceInter.delete(id);
        return "redirect:/people";
    }
}
