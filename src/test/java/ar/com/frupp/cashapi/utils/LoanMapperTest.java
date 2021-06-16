package ar.com.frupp.cashapi.utils;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.LoanModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoanMapperTest {
    private Loan entity;
    private LoanModel model;

    @BeforeEach
    void setUp() {
        Loan loan = new Loan();
        LoanModel loanModel = new LoanModel();

        int id = 2;
        loan.setId(id);
        loanModel.setId(2);

        double total = 1555.55;
        loan.setTotal(total);
        loanModel.setTotal(total);

        int userId = 5;
        User user = new User();
        user.setId(userId);
        loan.setUser(user);
        loanModel.setUserId(userId);

        this.entity = loan;
        this.model = loanModel;
    }

    @Test
    void shouldConvertEntityToModel() {
        LoanModel converted = LoanMapper.toModel(this.entity);

        assertThat(converted)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(this.model);
    }

    @Test
    void shouldConvertModelToEntity() {
        User user = new User();
        user.setId(this.model.getUserId());
        Loan converted = LoanMapper.toEntity(this.model, user);

        assertThat(converted)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(this.entity);
    }
}
