package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import org.springframework.data.domain.Page;

public interface LoanService {
    Page<Loan> findLoansPaginated(Integer userId, int page, int size);
}
