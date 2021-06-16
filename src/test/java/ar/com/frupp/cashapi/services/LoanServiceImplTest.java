package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LoanServiceImplTest {

    private LoanRepository repository;
    private LoanService service;

    @BeforeEach
    void setUp() {
        this.repository = Mockito.mock(LoanRepository.class);
        this.service = new LoanServiceImpl(this.repository);
    }

    @Test
    void whenUserIdIsNotNullShouldFilter() {
        this.service.findLoansPaginated(33, 2, 1);
        verify(this.repository, times(1)).findByUserId(anyInt(), any(Pageable.class));

    }

    @Test
    void whenUserIdIsNullShouldNotFilter() {
        this.service.findLoansPaginated(null, 2, 1);
        verify(this.repository, times(1)).findAll(any(Pageable.class));
    }
}