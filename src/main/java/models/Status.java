package models;

public enum Status {
    IN_PROGRESS, ACCEPTED, CANCELED;

    public static Status stringToStatus(String str) {
        if (str == "IN_PROGRESS") {
            return IN_PROGRESS;
        }
        if (str == "ACCEPTED") {
            return ACCEPTED;
        }
        return CANCELED;
    }
}
