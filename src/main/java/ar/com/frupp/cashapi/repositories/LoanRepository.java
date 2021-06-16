package ar.com.frupp.cashapi.repositories;

import ar.com.frupp.cashapi.entities.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
    Page<Loan> findByUserId(int userId, Pageable pageable);
}
