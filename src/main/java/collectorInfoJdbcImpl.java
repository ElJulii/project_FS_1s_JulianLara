import Data.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class collectorInfoJdbcImpl {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Arlet_0804";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrobka";

    public List<Game> getDataTable() {
        List<Game> listData = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM videogames";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Game game = new Game(
                                resultSet.getLong("id"),
                                resultSet.getString("type"),
                                resultSet.getString("namegame"),
                                resultSet.getDouble("price")
                        );
                        listData.add(game);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }
}
