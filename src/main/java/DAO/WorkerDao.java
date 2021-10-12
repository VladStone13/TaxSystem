package DAO;

import db.DataBaseConnection;
import models.User;
import models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WorkerDao implements Dao<Worker>{
    final private Connection connection = DataBaseConnection.getInstance().getConnection();

    public WorkerDao() throws SQLException {
    }

    @Override
    public Optional<Worker> get(UUID id) throws SQLException {
        String sql = "SELECT * FROM workers WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Worker worker = new Worker(
                resultSet.getString("name"),
                resultSet.getDouble("rating"),
                UUID.fromString(resultSet.getString("uuid")));

        return Optional.of(worker);
    }

    @Override
    public List<Worker> getAll() throws SQLException {
        List<Worker> result = new ArrayList<>();
        String sql = "SELECT * FROM workers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Worker worker = new Worker(
                    resultSet.getString("name"),
                    resultSet.getDouble("rating"),
                    UUID.fromString(resultSet.getString("uuid")));

            result.add(worker);
        }

        return result;
    }

    @Override
    public void save(Worker worker) throws SQLException {
        String sql = "INSERT INTO workers (name, rating, uuid) Values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, worker.getName());
        preparedStatement.setDouble(2, worker.getRating());
        preparedStatement.setString(3, worker.getUuid().toString());

        preparedStatement.execute();
    }

    @Override
    public void update(Worker worker, String[] params) {

    }

    @Override
    public void delete(Worker worker) throws SQLException {
        String sql = "DELETE * FROM workers WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, worker.getUuid().toString());

        preparedStatement.execute();
    }
}
