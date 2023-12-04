import Data.Game;
import Data.User;
import Interfaces.userRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;


@WebServlet("/home")
public class Home extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Arlet_0804";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrobka";

    private static final String SQL_SELECT_ALL = "Select * from users";
    private static final String SQL_GAMES = "SELECT * FROM videogames";


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
        request.getRequestDispatcher("/JSP/Home.jsp").forward(request, response);
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
        Long id_user = null;
        String name_user = "";
        Long id_game = null;
        String type_game = "";
        String name_game = "";
        Double total = 0.0;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            userRepository userRepository = new UserRepositoryJdbcImpl(connection);

            ResultSet resultSetUser = statement.executeQuery(SQL_SELECT_ALL);

            //info of game
            String idGame = request.getParameter("game");
            Long id = Long.parseLong(idGame);

            //info of user
            Long idUser = userRepository.getIdByCookie(valorCookie);
            String nameUser = userRepository.getNameByCookie(valorCookie);

            while (resultSetUser.next()) {
                User user = new User(
                        resultSetUser.getLong("id"),
                        resultSetUser.getString("name"),
                        resultSetUser.getString("lastname"),
                        resultSetUser.getString("mail"),
                        resultSetUser.getString("password")
                );
                if (idUser.equals(user.getId()) && nameUser.equals(user.getName())) {
                    id_user = user.getId();
                    name_user = user.getName();
                }
            }

            ResultSet resultSetGames = statement.executeQuery(SQL_GAMES);
            resultSetGames.next();

            while (resultSetGames.next()) {
                Game game = new Game(
                        resultSetGames.getLong("id"),
                        resultSetGames.getString("type"),
                        resultSetGames.getString("namegame"),
                        resultSetGames.getDouble("price")
                );

                if (game.getId().equals(id)) {
                    id_game = game.getId();
                    type_game = game.getType();
                    name_game = game.getNamegame();
                    total = game.getPrice();
                }
            }

            String insertSql = "INSERT INTO shopping(id_game, type_game, name_game, id_user, name_user, total$) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setObject(1, id_game);
                preparedStatement.setObject(2, type_game);
                preparedStatement.setObject(3, name_game);
                preparedStatement.setObject(4, id_user);
                preparedStatement.setObject(5, name_user);
                preparedStatement.setObject(6, total);

                preparedStatement.executeUpdate();
            }
            response.sendRedirect("/confirm");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
