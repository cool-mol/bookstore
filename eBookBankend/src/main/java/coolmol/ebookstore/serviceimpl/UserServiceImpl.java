package coolmol.ebookstore.serviceimpl;

import coolmol.ebookstore.dao.IconDao;
import coolmol.ebookstore.dao.UserDao;
import coolmol.ebookstore.entity.Icon;
import coolmol.ebookstore.entity.User;
import coolmol.ebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IconDao iconDao;

    @Override
    public User checkUser(String username, String password) {
        System.out.println(userDao.checkUser(username, password));
        return userDao.checkUser(username, password);
    }

    @Override
    public User addUser(String username, String password, String name, String tel, String email, String address) {
        System.out.println("I'm service in addUser");
        Icon icon = iconDao.findOne(1);
        return userDao.addUser(username, password, name, tel, email, address, icon);
    }

    @Override
    public List<Icon> showIcons() {
        return iconDao.getIcons();
    }

    @Override
    public User changeIcon(Integer userID, Integer iconID) {
        Icon icon = iconDao.findOne(iconID);
        System.out.println("I'm service for change");
        return userDao.changeIcon(userID, icon);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User changeOnesBanState(Integer userID) {
        return userDao.changeOnesBanState(userID);
    }
}
