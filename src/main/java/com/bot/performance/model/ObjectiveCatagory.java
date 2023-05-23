package com.bot.performance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "objective_catagory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectiveCatagory {
    @Id
    @Column(name = "ObjectiveCatagoryType")
    public String objectiveCatagoryType;
    @Column(name = "TypeDescription")
    public String typeDescription;
    @Column(name = "IsTagByRole")
    public boolean isTagByRole;
    @Column(name = "IsTagByDepartment")
    public boolean isTagByDepartment;
    @Column(name = "TagIds")
    public String tagIds;
    @Column(name = "FromDate")
    public Date fromDate;
    @Column(name = "ToDate")
    public Date toDate;
    @Column(name = "CreatedBy")
    public Long createdBy;
    @Column(name = "UpdatedBy")
    public Long updatedBy;
    @Column(name = "CreatedOn")
    public Date createdOn;
    @Column(name = "UpdatedOn")
    public Date updatedOn;
}
