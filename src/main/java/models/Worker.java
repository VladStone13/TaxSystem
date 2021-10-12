package models;


import java.util.UUID;

public class Worker {
    private double rating;
    private String name;

    private UUID uuid;

    public Worker(String name, double rating) {
        this.name = name;
        this.rating = rating;
        uuid = UUID.randomUUID();
    }

    public Worker(String name, double rating, UUID uuid) {
        this.name = name;
        this.rating = rating;
        this.uuid = uuid;
    }

    public void setProfit(double profit) {
        this.rating = profit;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rating=" + rating +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
