
import Data.UUID;
import Data.User;
import Interfaces.userRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements userRepository {
    private final Connection connection;
    private static final String SQL_SELECT_ALL = "Select * from users";

    protected UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List find_password_mail(String mail, String password) {
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("mail"),
                        resultSet.getString("password")
                );
                if (user.getMail().equals(mail) && user.getPassword().equals(password)) {
                    result.add(user);
                    System.out.println("Good");
                }
            }
            return result;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Long getIdByPassword(String password) {
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("mail"),
                        resultSet.getString("password")

                );
                if (user.getPassword().equals(password)) {
                    return user.getId();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Long getIdByCookie(String cookie) {
        String sql = "SELECT u.id " +
                "FROM users u " +
                "JOIN user_uuid uu ON u.id = uu.id_user " +
                "WHERE uu.uuid = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cookie);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String getNameByCookie(String cookie) {
        String selectSql = "SELECT u.name " +
                "FROM users u " +
                "JOIN user_uuid uu ON u.id = uu.id_user " +
                "WHERE uu.uuid = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, cookie);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
