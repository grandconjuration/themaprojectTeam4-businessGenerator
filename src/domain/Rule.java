package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import domain.Value;

@Entity
@Table(name = "RULE")
public class Rule {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "TOBEGENERATED")
	private Boolean toBeGenerated = false;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ERRORMESSAGE")
	private String errorMessage;

	@Column(name = "GENERATEDCODE")
	private String generatedCode;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "RULECOLUMNS", joinColumns = @JoinColumn(name = "RULE_ID"), inverseJoinColumns = @JoinColumn(name = "COLUMN_ID"))
	private List<AppColumn> allColumns = new ArrayList<AppColumn>();

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "rule")
	private List<Value> allValues = new ArrayList<Value>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RULETYPE_ID", nullable = false)
	private RuleType ruleType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "OPERATOR_ID", nullable = false)
	private Operator operator;

	public Rule() {

	}

	public Rule(String name, String code, String description,
			String errorMessage, String generatedCode, boolean toBeGenerated,
			int id, List<AppColumn> allColumns, List<Value> allValues,
			RuleType ruleType, Operator operator) {
		setCode(code);
		setDescription(description);
		setErrorMessage(errorMessage);
		setGeneratedCode(generatedCode);
		setToBeGenerated(toBeGenerated);
		setId(id);
		setAllColumns(allColumns);
		setAllValues(allValues);
		setRuleType(ruleType);
		setOperator(operator);
	}

	public void addColumn(AppColumn column) {
		allColumns.add(column);
	}

	public void addValue(Value value) {
		allValues.add(value);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public boolean isToBeGenerated() {
		return toBeGenerated;
	}

	public void setToBeGenerated(boolean toBeGenerated) {
		this.toBeGenerated = toBeGenerated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AppColumn> getAllColumns() {
		return allColumns;
	}

	public void setAllColumns(List<AppColumn> allColumns) {
		this.allColumns = allColumns;
	}

	public List<Value> getAllValues() {
		return allValues;
	}

	public void setAllValues(List<Value> allValues) {
		this.allValues = allValues;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public String returnValue(int number){
		String s = null;
		for (Value v : allValues){
			if(v.getValueNumber() == number){
				s = v.getValue();
			}
		}
		return s;
	}
	
	public String returnColumn(int number){
		String s = null;
		for (AppColumn c : allColumns){
			if(c.getId() == number){
				s = c.getName();
			}
		}
		return s;
	}

}
