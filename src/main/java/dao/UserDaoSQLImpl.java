package dao;

import api.UserDao;
import entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSQLImpl implements UserDao {
    private Connection connection;
    private final String databaseName = "management";
    private final String tableName = "users";
    private final String user = "root";
    private final String password = "artur";
    private static UserDaoSQLImpl instance;

    public static UserDaoSQLImpl getInstance(){
        if(instance == null){
            instance = new UserDaoSQLImpl();
        }
        return instance;
    }

    private UserDaoSQLImpl(){
        init();
    }

    private void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseName+"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveUser(User user) {
        PreparedStatement preparedStatement = null;

        try{
            String query = "insert into "+tableName+" (login,password) values(?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserByLogin(String login) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "delete from "+tableName+" where login = '"+login+"'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "delete from "+tableName+" where id="+id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Statement statement = null;

        try{
            String query = "select * from "+tableName;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Long id = Long.valueOf(resultSet.getInt("id"));
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(id,login,password));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return users;
    }

    public void updateUser(User user) {
        PreparedStatement preparedStatement = null;

        try{
            String query = "update "+tableName+" set login=?, password=? where id="+user.getId();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
