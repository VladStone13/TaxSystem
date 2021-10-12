package models;

import java.math.BigDecimal;
import java.util.UUID;

public class TaxReportForm {
    private UUID uuid;
    private String reportText;
    private BigDecimal price;
    private Status status;
    private User user;
    private Worker worker;

    public TaxReportForm(String reportText, BigDecimal price, Status status, User user, Worker worker) {
        this.status = status;
        this.price = price;
        this.reportText = reportText;
        this.user = user;
        this.worker = worker;
        uuid = UUID.randomUUID();
    }

    public TaxReportForm(String reportText, BigDecimal price, Status status, UUID uuid, User user, Worker worker) {
        this.status = status;
        this.price = price;
        this.reportText = reportText;
        this.user = user;
        this.worker = worker;
        this.uuid = uuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getReportText() {
        return reportText;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "TaxReportForm{" +
                "uuid=" + uuid +
                ", reportText='" + reportText + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", user=" + user +
                ", worker=" + worker +
                '}';
    }
}

