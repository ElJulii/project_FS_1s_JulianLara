import Data.Game;
import Interfaces.userRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/admin")
public class Admin extends HttpServlet {
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
        collectorInfoJdbcImpl collectorInfoJdbc = new collectorInfoJdbcImpl();
        List<Game> listData = collectorInfoJdbc.getDataTable();
        request.setAttribute("data", listData);
        request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String type = request.getParameter("type");
            String namegame = request.getParameter("namegame");
            Double price = Double.parseDouble(request.getParameter("price"));

            String insertToSql = "insert into videogames(type, namegame, price) values (?, ?, ?)";
            try (PreparedStatement preparedStatement= connection.prepareStatement(insertToSql)) {
                preparedStatement.setString(1, type);
                preparedStatement.setString(2, namegame);
                preparedStatement.setDouble(3, price);
                preparedStatement.executeUpdate();
            }
            response.sendRedirect("/admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
