package coolmol.ebookstore.service;

import coolmol.ebookstore.entity.Icon;
import coolmol.ebookstore.entity.User;
import org.springframework.context.annotation.Scope;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);

    User addUser(String username, String password, String name, String tel, String email, String address);

    List<Icon> showIcons();

    User changeIcon(Integer userID, Integer IconID);

    List<User> getAllUsers();

    User changeOnesBanState(Integer userID);
}
