package DAO;

import db.DataBaseConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements Dao<User>{
    final private Connection connection = DataBaseConnection.getInstance().getConnection();

    public UserDao() throws SQLException {
    }


    @Override
    public Optional<User> get(UUID id) throws SQLException {
        String sql = "SELECT * FROM users WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        User user = new User(
                resultSet.getString("firstName"),
                resultSet.getString("surName"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                UUID.fromString(resultSet.getString("uuid")));

        return Optional.of(user);
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString("firstName"),
                    resultSet.getString("surName"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    UUID.fromString(resultSet.getString("uuid")));

            result.add(user);
        }

        return result;
    }


    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users (firstName, surName, email, password, uuid) Values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getSurName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getUuid().toString());

        preparedStatement.execute();
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) throws SQLException {
        String sql = "DELETE * FROM users WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUuid().toString());

        preparedStatement.execute();
    }
}
