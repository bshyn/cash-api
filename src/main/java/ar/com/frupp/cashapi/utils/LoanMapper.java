package ar.com.frupp.cashapi.utils;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.LoanModel;

public class LoanMapper {
    public static LoanModel toModel(Loan entity) {
        return new LoanModel(
                entity.getId(), entity.getTotal(), entity.getUser().getId()
        );
    }

    public static Loan toEntity(LoanModel model, User userEntity) {
        Loan loan = new Loan();
        loan.setUser(userEntity);
        loan.setTotal(model.getTotal());
        loan.setId(model.getId());

        return loan;
    }
}
