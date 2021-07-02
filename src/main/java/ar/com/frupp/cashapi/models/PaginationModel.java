package ar.com.frupp.cashapi.models;

public class PaginationModel {
    private Integer page;
    private Integer size;
    private Long total;

    public PaginationModel(Integer page, Integer size, Long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PaginationModel() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaginationModel{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
