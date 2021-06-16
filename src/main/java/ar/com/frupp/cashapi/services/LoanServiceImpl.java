package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.repositories.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Loan> findLoansPaginated(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.repository.findByUserId(userId, pageable);
    }
}
