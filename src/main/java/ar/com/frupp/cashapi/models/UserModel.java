package ar.com.frupp.cashapi.models;

import java.util.Collection;

public class UserModel {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<LoanModel> loans;

    public UserModel(int id, String email, String firstName, String lastName, Collection<LoanModel> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public UserModel() {
    }

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

    public Collection<LoanModel> getLoans() {
        return loans;
    }

    public void setLoans(Collection<LoanModel> loans) {
        this.loans = loans;
    }
}
