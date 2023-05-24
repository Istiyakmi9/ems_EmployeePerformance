package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class FilterModel {
    @JsonProperty("IsActive")
    boolean isActive;
    @JsonProperty("SearchString")
    String searchString;
    @JsonProperty("PageIndex")
    int pageIndex;
    @JsonProperty("PageSize")
    int pageSize;
    @JsonProperty("SortBy")
    String sortBy;
    int companyId;;
    int offsetIndex;
    Long employeeId;
    @JsonProperty("SortDirection")
    String sortDirection;

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getOffsetIndex() {
        return offsetIndex;
    }

    public void setOffsetIndex(int offsetIndex) {
        this.offsetIndex = offsetIndex;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public FilterModel(boolean isActive, String searchString, int pageIndex, int pageSize, String sortBy, int companyId, int offsetIndex, Long employeeId) {
        this.isActive = isActive;
        this.searchString = searchString;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.companyId = companyId;
        this.offsetIndex = offsetIndex;
        this.employeeId = employeeId;
    }

    public FilterModel() {}
}
