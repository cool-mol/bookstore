package coolmol.ebookstore.controller;

import coolmol.ebookstore.entity.Icon;
import coolmol.ebookstore.entity.User;
import coolmol.ebookstore.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import coolmol.ebookstore.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public User login(@RequestBody Map<String, String> params) {
        System.out.println("login controller");
        String username = params.get("username");
        String password = params.get("password");
        return userService.checkUser(username, password);
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody Map<String, String> params) {
        System.out.println("I'm controller");
        String username = params.get("username");
        String password = params.get("password");
        String name = params.get("name");
        String tel = params.get("tel");
        String email = params.get("email");
        String address = params.get("address");
        return userService.addUser(username, password, name, tel, email, address);
    }

    @RequestMapping("/showIcons")
    public List<Icon> showIcons() {
        return userService.showIcons();
    }

    @PostMapping("/changeIcon/{userID}/{iconID}")
    public User changeOnesIcon(@PathVariable Integer userID, @PathVariable Integer iconID) {
        System.out.println("i'm controller for changeIcon.");
        return userService.changeIcon(userID, iconID);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/changeBanState/{userID}")
    public User banUser(@PathVariable Integer userID){
        return userService.changeOnesBanState(userID);
    }
}
