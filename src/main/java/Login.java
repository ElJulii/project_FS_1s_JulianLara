import Data.User;
import Interfaces.userRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Arlet_0804";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrobka";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/LogIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            userRepository userRepository = new UserRepositoryJdbcImpl(connection);
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            List<User> mail_password = userRepository.find_password_mail(mail, password);


            if (!mail_password.isEmpty()) {
                String cookieUuid = UUID.randomUUID().toString();
                saveCookiesDATA(password, cookieUuid);

                Cookie cookie = new Cookie("user_cookie", cookieUuid);
                response.addCookie(cookie);
                System.out.println("The user has entered");

                if(mail.equals("mateo2003@gmail.com")) {
                    response.sendRedirect("/admin");
                } else {
                    response.sendRedirect("/home");
                }
            } else {
                System.out.println("The user has not entered");
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
    protected void saveCookiesDATA(String password, String cookieUid) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            userRepository userRepository = new UserRepositoryJdbcImpl(connection);

            Long userId = userRepository.getIdByPassword(password);
            int intUserId = userId.intValue();

            String insertQuery = "INSERT INTO user_uuid(id_user, uuid) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, intUserId);
                preparedStatement.setString(2, cookieUid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
