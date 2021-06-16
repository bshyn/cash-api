package ar.com.frupp.cashapi.models;

import java.util.Collection;

public class GetLoansResponse {
    private Collection<LoanModel> items;
    private PaginationModel paging;

    public GetLoansResponse(Collection<LoanModel> items, PaginationModel paging) {
        this.items = items;
        this.paging = paging;
    }

    public GetLoansResponse() {
    }

    public Collection<LoanModel> getItems() {
        return items;
    }

    public void setItems(Collection<LoanModel> items) {
        this.items = items;
    }

    public PaginationModel getPaging() {
        return paging;
    }

    public void setPaging(PaginationModel paging) {
        this.paging = paging;
    }
}
