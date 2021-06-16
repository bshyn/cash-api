package ar.com.frupp.cashapi.entities;

import javax.persistence.*;

@Entity
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double total;

    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
