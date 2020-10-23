package ru.service.db.dao;

import ru.service.db.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersDaoJdbcImpl implements EmployeesDao {

  //language=SQL
  private final String SQL_SELECT_ALL =
      "SELECT * FROM users";

  //language=SQL
  private final String SQL_SELECT_BY_ID =
      "SELECT * FROM users WHERE user_id = ?";

  //language=SQL
  private final String SQL_SELECT_BY_NAME =
      "SELECT * FROM users WHERE user_name = ?";

  private final String SQL_INSERT_NEW_USER =
      "INSERT INTO users (user_name, password) VALUES (?, ?)";

  private Connection connection;

  public UsersDaoJdbcImpl(DataSource dataSource) {
    try {
      this.connection = dataSource.getConnection();
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Optional<User> findByName(String firstName) {
    try {
      PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_NAME);
      statement.setString(1, firstName);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Integer userId = resultSet.getInt("user_id");
        String userName = resultSet.getString("user_name");
        String password = resultSet.getString("password");

        User user = User.builder()
            .id(userId)
            .userName(userName)
            .password(password)
            .build();

        return Optional.of(user);

      }
      return Optional.empty();
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void save(User model) {
    try {
      PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_USER);
      statement.setString(1, model.getUserName());
      statement.setString(2, model.getPassword());
      statement.execute();

    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void update(User model) {

  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public List<User> findAll() {
    try {
      List<User> users = new ArrayList<>();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
      while (resultSet.next()) {
        Integer userId = resultSet.getInt("user_id");
        String userName = resultSet.getString("user_name");
        String password = resultSet.getString("password");

        User user = User.builder()
            .id(userId)
            .userName(userName)
            .password(password)
            .build();

        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }
}
