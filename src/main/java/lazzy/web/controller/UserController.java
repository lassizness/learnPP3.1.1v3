package lazzy.web.controller;

import lazzy.web.model.User;
import lazzy.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController //херота для работы по джсон без представления
@Controller
//@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "editUser"; // removed leading slash
    }

    @PostMapping("/users/edit")
    public String editUser(@RequestParam("id")long id, User user) {//как костыль пришлось добавить сетер в модель
        user.setId(id);
        userService.updateUser(user);

        return "redirect:/users";
    }
}