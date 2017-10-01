package entities;

public enum Roles {
    ADMIN(0),
    USER(1);

    Roles(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
}
