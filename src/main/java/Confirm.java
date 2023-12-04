import Interfaces.userRepository;
import com.sun.jdi.LongValue;
import jdk.vm.ci.meta.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/confirm")
@MultipartConfig
public class Confirm extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Arlet_0804";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrobka";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/Confirm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cookieValue = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user_cookie")) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO images (id_game, image) VALUES (?, ?)")) {
            userRepository userRepository = new UserRepositoryJdbcImpl(connection);

            Long idGame = userRepository.getIdByCookie(cookieValue);
            int id = idGame.intValue();


            Part filePart = request.getPart("file");


            preparedStatement.setInt(1, id);
            preparedStatement.setBinaryStream(2, filePart.getInputStream());

            preparedStatement.executeUpdate();

            response.sendRedirect("/home");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
