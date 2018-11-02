package api;

import entity.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserDao
{
    void saveUser(User user) throws FileNotFoundException;
    void saveUsers(List<User> users) throws FileNotFoundException;

    void removeUserByLogin(String login) throws IOException;
    void removeUserById(Long id) throws IOException;

    List<User> getAllUsers() throws IOException;
    //User getUserByLogin(String login);
    //User getUserById(Long userId);



}
