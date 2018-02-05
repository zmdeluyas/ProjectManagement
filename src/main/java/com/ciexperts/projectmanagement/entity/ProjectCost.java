package com.ciexperts.projectmanagement.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_Cost")
public class ProjectCost {
	
	@Id @GeneratedValue
	@Column(name = "pc_no")
	private Integer pcNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "total_budget")
	private BigDecimal totalBudget;
	
	@Column(name = "budget_to_date")
	private BigDecimal budgetToDate;
	
	@Column(name = "actual_to_date")
	private BigDecimal actualToDate;
	
	@Column(name = "status")
	private Integer status;
	
	public ProjectCost() {
		super();
	}
	public ProjectCost(Integer pcNo, Integer projNo, BigDecimal totalBudget,
			BigDecimal budgetToDate, BigDecimal actualToDate, Integer status) {
		super();
		this.pcNo = pcNo;
		this.projNo = projNo;
		this.totalBudget = totalBudget;
		this.budgetToDate = budgetToDate;
		this.actualToDate = actualToDate;
		this.status = status;
	}
	public Integer getPcNo() {
		return pcNo;
	}
	public void setPcNo(Integer pcNo) {
		this.pcNo = pcNo;
	}
	public Integer getProjNo() {
		return projNo;
	}
	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}
	public BigDecimal getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(BigDecimal totalBudget) {
		this.totalBudget = totalBudget;
	}
	public BigDecimal getBudgetToDate() {
		return budgetToDate;
	}
	public void setBudgetToDate(BigDecimal budgetToDate) {
		this.budgetToDate = budgetToDate;
	}
	public BigDecimal getActualToDate() {
		return actualToDate;
	}
	public void setActualToDate(BigDecimal actualToDate) {
		this.actualToDate = actualToDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.pcNo = status;
	}
}
