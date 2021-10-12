package DAO;

import db.DataBaseConnection;
import models.Status;
import models.TaxReportForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaxReportFormDao implements Dao<TaxReportForm> {
    final private Connection connection = DataBaseConnection.getInstance().getConnection();
    final private UserDao userDao = new UserDao();
    final private WorkerDao workerDao = new WorkerDao();

    public TaxReportFormDao() throws SQLException {
    }

    @Override
    public Optional<TaxReportForm> get(UUID id) throws SQLException {
        String sql = "SELECT * FROM tax_reports WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id.toString());

        ResultSet resultSet = preparedStatement.executeQuery();

        if(!resultSet.next()) {
            return Optional.empty();
        }

        TaxReportForm taxReportForm = new TaxReportForm(
                resultSet.getString("reportText"),
                resultSet.getBigDecimal("price"),
                Status.stringToStatus(resultSet.getString("status")),
                UUID.fromString(resultSet.getString("uuid")),
                userDao.get(UUID.fromString(resultSet.getString("user_id"))).get(),
                workerDao.get(UUID.fromString(resultSet.getString("worker_id"))).get());

        return Optional.of(taxReportForm);
    }

    @Override
    public List<TaxReportForm> getAll() throws SQLException {
        List<TaxReportForm> result = new ArrayList<>();

        String sql = "SELECT * FROM tax_reports";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            TaxReportForm taxReportForm = new TaxReportForm(
                    resultSet.getString("reportText"),
                    resultSet.getBigDecimal("price"),
                    Status.stringToStatus(resultSet.getString("status")),
                    UUID.fromString(resultSet.getString("uuid")),
                    userDao.get(UUID.fromString(resultSet.getString("user_id"))).get(),
                    workerDao.get(UUID.fromString(resultSet.getString("worker_id"))).get());

            result.add(taxReportForm);
        }

        return result;
    }

    @Override
    public void save(TaxReportForm taxReportForm) throws SQLException {
        String sql = "INSERT INTO tax_reports (reportText, price, status, uuid, user_id, worker_id) Values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, taxReportForm.getReportText());
        preparedStatement.setBigDecimal(2, taxReportForm.getPrice());
        preparedStatement.setString(3, taxReportForm.getStatus().toString());
        preparedStatement.setString(4, taxReportForm.getUuid().toString());
        preparedStatement.setString(5, taxReportForm.getUser().getUuid().toString());
        preparedStatement.setString(6, taxReportForm.getWorker().getUuid().toString());

        preparedStatement.execute();
    }

    @Override
    public void update(TaxReportForm taxReportForm, String[] params) {
        return;
    }

    @Override
    public void delete(TaxReportForm taxReportForm) throws SQLException {
        String sql = "DELETE * FROM tax_reports WHERE uuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, taxReportForm.getUuid().toString());

        preparedStatement.execute();
    }
}
