package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Column(name = "CODE", nullable = true)
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ERRORMESSAGE")
	private String errorMessage;

	@Column(name = "GENERATEDCODE")
	private String generatedCode;

//	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//	@JoinTable(name = "RULECOLUMNS", joinColumns = @JoinColumn(name = "RULE_ID"), inverseJoinColumns = @JoinColumn(name = "COLUMN_ID"))
//	private List<AppColumn> allColumns = new ArrayList<AppColumn>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.rule", cascade = CascadeType.ALL)
	private Set<RuleColumn> ruleColumns = new HashSet<RuleColumn>();

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "rule")
	private List<Value> allValues = new ArrayList<Value>();

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "RULETYPE_ID", nullable = false)
	private RuleType ruleType;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "OPERATOR_ID", nullable = true)
	private Operator operator;
	
	@JoinColumn(name ="INSERTTRIGGER")
	private Boolean insertTrigger = false;
	
	@JoinColumn(name = "UPDATETRIGGER")
	private Boolean updateTrigger = false;
	
	@JoinColumn(name = "DELETETRIGGER")
	private Boolean deleteTrigger = false;

	public Boolean getInsertTrigger() {
		return insertTrigger;
	}

	public void setInsertTrigger(Boolean insertTrigger) {
		this.insertTrigger = insertTrigger;
	}

	public Boolean getUpdateTrigger() {
		return updateTrigger;
	}

	public void setUpdateTrigger(Boolean updateTrigger) {
		this.updateTrigger = updateTrigger;
	}

	public Boolean getDeleteTrigger() {
		return deleteTrigger;
	}

	public void setDeleteTrigger(Boolean deleteTrigger) {
		this.deleteTrigger = deleteTrigger;
	}

	public Rule() {

	}

	public Rule(String name, String code, String description,
			String errorMessage, String generatedCode, boolean toBeGenerated,
			int id, Set<RuleColumn> allRuleColumns, List<Value> allValues,
			RuleType ruleType, Operator operator) {
		setCode(code);
		setDescription(description);
		setErrorMessage(errorMessage);
		setGeneratedCode(generatedCode);
		setToBeGenerated(toBeGenerated);
		setId(id);
		setAllRuleColumns(allRuleColumns);
		setAllValues(allValues);
		setRuleType(ruleType);
		setOperator(operator);
	}

	public void addRuleColumn(RuleColumn rc) {
		ruleColumns.add(rc);
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

	public Set<RuleColumn> getAllRuleColumns() {
		return this.ruleColumns;
	}

	public void setAllRuleColumns(Set<RuleColumn> rc) {
		this.ruleColumns = rc;
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
		for (RuleColumn c : this.ruleColumns){
			if(c.getNumber() == number){
				s = c.getColumn().getName();
			}
		}
		return s;
	}
	
	public AppColumn getColumnByNumber(int number) {
		AppColumn aCol = null;
		for (RuleColumn c : this.getAllRuleColumns()) {
			if(c.getNumber() == number) {
				aCol = c.getColumn();
			}
		}
		return aCol;
		
	}

}
