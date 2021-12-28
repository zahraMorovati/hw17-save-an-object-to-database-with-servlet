import dao.UserDao;
import lombok.SneakyThrows;
import model.User;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServlet extends HttpServlet {

    private UserDao userDao;

    public void init() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String family = request.getParameter("family");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Date birthDate = new SimpleDateFormat("yyyy-mm-dd")
                .parse(request.getParameter("birthDate"));

        String email = request.getParameter("email");
        User newUser = User.UserBuilder.anUser()
                .setName(name)
                .setFamily(family)
                .setUsername(username)
                .setPassword(password)
                .setBirthDate(birthDate)
                .setEmail(email).build();
        userDao.saveUser(newUser);
        System.out.println("user saved!");
        response.sendRedirect("userSaved.html");

    }

}
