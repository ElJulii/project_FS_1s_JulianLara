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

@WebServlet("/edit")
public class Edit extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Arlet_0804";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrobka";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/EditUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String valorCookie = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                valorCookie = cookie.getValue();
            }
        }


        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            userRepository userRepository = new UserRepositoryJdbcImpl(connection);

            Long idUser = userRepository.getIdByCookie(valorCookie);

            String newPassword = request.getParameter("password");
            String nameAdmin = userRepository.getNameByCookie(valorCookie);

            String insertSql = "UPDATE users SET password = ? Where id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setObject(1, newPassword);
                preparedStatement.setObject(2, idUser);

                int changed = preparedStatement.executeUpdate();

                if (changed > 0) {
                    System.out.println("The password was changed");
                    System.out.println(nameAdmin);
                    System.out.println(idUser);

                    if (nameAdmin != null && nameAdmin.equals("Admin")) {
                        response.sendRedirect("/admin"); // esta es la línea 56
                    } else {
                        response.sendRedirect("/home");
                    }

                } else System.out.println("the password was not changed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
