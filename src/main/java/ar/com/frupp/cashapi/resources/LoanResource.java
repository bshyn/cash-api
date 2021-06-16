package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.models.GetLoansResponse;
import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.PaginationModel;
import ar.com.frupp.cashapi.services.LoanService;
import ar.com.frupp.cashapi.utils.LoanMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loans")
public class LoanResource {

    private LoanService loanService;

    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    @ResponseBody
    public GetLoansResponse getAllLoansPaginated(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(name = "user_id", required = false) Integer userId) {
        Page<Loan> pagedLoans = this.loanService.findLoansPaginated(userId, page, size);

        PaginationModel paging = new PaginationModel(
                pagedLoans.getNumber(), pagedLoans.getSize(), pagedLoans.getTotalElements()
        );

        Collection<LoanModel> items = pagedLoans.get()
                .map(LoanMapper::toModel).collect(Collectors.toList());

        return new GetLoansResponse(items, paging);
    }
}
