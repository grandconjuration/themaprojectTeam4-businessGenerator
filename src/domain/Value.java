package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VALUE")
public class Value {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "VALUENUMBER")
	private int valueNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_ID")	
	private Rule rule;

	public Value(){
		
	}
	
	public Value(String type, String value, int valueNumber, int id) {
		setType(type);
		setValue(value);
		setValueNumber(valueNumber);
		setId(id);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getValueNumber() {
		return valueNumber;
	}

	public void setValueNumber(int valueNumber) {
		this.valueNumber = valueNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setRule(Rule r){
		this.rule = r;
	}
	
	public Rule getRule(){
		return rule;
	}
	
	

}

