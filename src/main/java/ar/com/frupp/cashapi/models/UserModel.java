package ar.com.frupp.cashapi.models;

import java.util.Collection;

public class UserModel {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<LoanModel> loans;

    public UserModel(Integer id, String email, String firstName, String lastName, Collection<LoanModel> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public UserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loans=" + (loans == null ? "null" : loans.toString()) +
                '}';
    }
}
