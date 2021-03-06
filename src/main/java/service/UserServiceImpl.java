package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoSQLImpl;
import entity.User;
import exception.userException.UserLoginAlreadyExistException;
import exception.userException.UserShortLengthPasswordException;
import exception.userException.UserShortLoginLengthException;
import validator.UserValidator;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = null;
    private UserDao userDao = UserDaoSQLImpl.getInstance();
    private UserValidator userValidator = UserValidator.getInstance();

    private UserServiceImpl(){
    }

    public static UserServiceImpl getInstance(){
        if(instance==null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    public User getUserById(Long userId) throws IOException {
        List<User> users = getAllUsers();

        for(User user : users){
            if(user.getId().equals(userId)) return user;
        }
        return null;
    }

    public User getUserByLogin(String login) throws IOException {
        List<User> users = getAllUsers();

        for(User user : users){
            if(user.getLogin().equals(login)) return user;
        }
        return null;
    }

    public boolean addUser(User user) throws UserShortLengthPasswordException, UserShortLoginLengthException, UserLoginAlreadyExistException, IOException {
            if(userValidator.isValidate(user) && isUserByLoginNotExist(user.getLogin())){
                userDao.saveUser(user);
                return true;
            }
        return false;
    }

    public boolean isLogedConfirm(String login, String password) throws IOException {
        List<User> users = getAllUsers();

        for(User user : users)
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) return true;
        return false;
    }

    public void removeUserById(Long userId) throws IOException {
        userDao.removeUserById(userId);
    }

    public void removeUserByName(String name) throws IOException {
        userDao.removeUserByLogin(name);
    }

    public boolean isUserByLoginNotExist(String login) throws UserLoginAlreadyExistException, IOException {
        List<User> users = getAllUsers();

        for(User user : users){
            if(user.getLogin().equals(login)) throw new UserLoginAlreadyExistException("Już istnieje taki user z takim loginem");
        }

        return true;
    }
}
