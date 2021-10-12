import DAO.TaxReportFormDao;
import DAO.UserDao;
import DAO.WorkerDao;
import db.DataBaseConnection;
import models.Status;
import models.TaxReportForm;
import models.User;
import models.Worker;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws SQLException {

        TaxReportFormDao taxReportFormDao = new TaxReportFormDao();
        UserDao userDao = new UserDao();
        WorkerDao workerDao = new WorkerDao();

        User user = new User("Dima", "Pavl", "13102321@gmail", "13023");
        Worker worker = new Worker("Tima", 8.5);

        userDao.save(user);
        workerDao.save(worker);

        System.out.println(userDao.get(user.getUuid()));
        System.out.println(workerDao.get(worker.getUuid()));

        TaxReportForm reportForm = new TaxReportForm("Good day", new BigDecimal(15.3), Status.IN_PROGRESS, user, worker);
        System.out.println(taxReportFormDao.get(reportForm.getUuid()));

        System.out.println(userDao.getAll());

        System.out.println(workerDao.getAll());

        System.out.println(taxReportFormDao.getAll());
    }
}
