package ar.com.frupp.cashapi.models;

public class LoanModel {
    private int id;
    private double total;
    private int userId;

    public LoanModel(int id, double total, int userId) {
        this.id = id;
        this.total = total;
        this.userId = userId;
    }

    public LoanModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoanModel{" +
                "id=" + id +
                ", total=" + total +
                ", userId=" + userId +
                '}';
    }
}
