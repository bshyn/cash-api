package ar.com.frupp.cashapi.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user")
    private Collection<Loan> loans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Collection<Loan> loans) {
        this.loans = loans;
    }
}
