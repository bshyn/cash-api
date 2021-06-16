package ar.com.frupp.cashapi.utils;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.UserModel;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserModel toModel(User entity) {
        Collection<LoanModel> loans = entity.getLoans().stream()
                .map(LoanMapper::toModel).collect(Collectors.toList());

        return new UserModel(
                entity.getId(), entity.getEmail(), entity.getFirstName(),
                entity.getLastName(), loans);
    }

    public static User toEntity(UserModel model) {
        User user = new User();
        user.setId(model.getId());
        user.setEmail(model.getEmail());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());

        Collection<Loan> loans = model.getLoans().stream()
                .map((loanModel) -> LoanMapper.toEntity(loanModel, user))
                .collect(Collectors.toList());
        user.setLoans(loans);

        return user;
    }
}
