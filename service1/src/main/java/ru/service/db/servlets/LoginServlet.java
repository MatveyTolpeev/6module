package ru.service.db.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.service.db.dao.UsersDaoJdbcImpl;
import ru.service.db.dao.EmployeesDao;
import ru.service.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private String passwordForAllUsers = "qwerty007";

  private EmployeesDao employeesDao;

  @Override
  public void init() throws ServletException {
    Properties properties = new Properties();
    DriverManagerDataSource dataSource =
        new DriverManagerDataSource();

    try {
      properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
      String dbUrl = properties.getProperty("db.url");
      String dbUsername = properties.getProperty("db.username");
      String dbPassword = properties.getProperty("db.password");

      dataSource.setUsername(dbUsername);
      dataSource.setPassword(dbPassword);
      dataSource.setUrl(dbUrl);
      dataSource.setDriverClassName("org.postgresql.Driver");

      employeesDao = new UsersDaoJdbcImpl(dataSource);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter("user_name");
    String password = req.getParameter("password");
    Optional<User> user = employeesDao.findByName(userName);

    if (user.isPresent() && password.equals(user.get().getPassword())) {
      // создаем для него сессию
      HttpSession session = req.getSession();
      // кладем в атрибуты сессии атрибут user с именем пользователя
      session.setAttribute("UserName",req.getParameter("user_name"));
      session.setAttribute("user", user.get().toString());
      // перенаправляем на страницу home
      req.getServletContext().getRequestDispatcher("/directory").forward(req, resp);
    } else {
      resp.sendRedirect(req.getContextPath() + "/login");
    }
  }
}
