package ar.com.frupp.cashapi.resources;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.GetLoansResponse;
import ar.com.frupp.cashapi.services.LoanService;
import ar.com.frupp.cashapi.utils.LoanMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(LoanResource.class)
public class LoanResourceTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String BASE_PATH = "/loans";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoanService service;

    @Value("classpath:tests/resources/loan/get_loans_request.json")
    private Resource resourceFile;

    @Test
    void shouldReturnUserLoans() throws Exception {
        GetLoansResponse expected = mapper.readValue(resourceFile.getURL(), GetLoansResponse.class);
        List<Loan> loansAsList = expected.getItems().stream()
                .map(loan -> {
                    User user = new User();
                    user.setId(loan.getUserId());
                    return LoanMapper.toEntity(loan, user);
                })
                .collect(Collectors.toList());

        int page = expected.getPaging().getPage();
        int size = expected.getPaging().getSize();
        long total = expected.getPaging().getTotal();
        int userId = loansAsList.get(0).getUser().getId();

        Pageable paging = PageRequest.of(page, size);
        Page<Loan> pagedLoans = new PageImpl(loansAsList, paging, total);

        Mockito.when(service.findLoansPaginated(userId, page, size)).thenReturn(pagedLoans);

        MvcResult result = mvc.perform(
                get(BASE_PATH)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("user_id", String.valueOf(userId))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andReturn();

        String responseBody = mapper.writeValueAsString(expected);

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(responseBody);
    }

    @Test
    void shouldReturn400IfNoPageOrSize() throws Exception {
        this.mvc.perform(
                get(BASE_PATH)
        ).andExpect(
                status().isBadRequest()
        );

        this.mvc.perform(
                get(BASE_PATH)
                        .requestAttr("page", "")
                        .requestAttr("size", "")
        ).andExpect(
                status().isBadRequest()
        );
    }
}
