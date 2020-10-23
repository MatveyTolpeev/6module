package ru.service.db.models;

public class User {

  public User(Integer id, String userName, String password) {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }

  public static class UserBuilder {

    private Integer id;
    private String userName;
    private String password;

    public UserBuilder id(Integer userId) {
      this.id = userId;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder userName(String userName) {
      this.userName = userName;
      return this;
    }

    public User build() {
      return new User(id, userName, password);
    }
  }

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  private Integer id;
  private String userName;
  private String password;
}
