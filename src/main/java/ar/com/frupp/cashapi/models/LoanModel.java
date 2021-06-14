package ar.com.frupp.cashapi.models;

public class LoanModel {
    private String id;
    private double total;
    private int userId;

    public LoanModel(String id, double total, int userId) {
        this.id = id;
        this.total = total;
        this.userId = userId;
    }

    public LoanModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
