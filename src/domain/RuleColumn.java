package domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "RULECOLUMNS")
@AssociationOverrides({
	@AssociationOverride(name = "pk.rule", 
		joinColumns = @JoinColumn(name = "RULE_ID")),
	@AssociationOverride(name = "pk.column", 
		joinColumns = @JoinColumn(name = "COLUMN_ID")) })
public class RuleColumn implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3963457829159618473L;

	@EmbeddedId
	private RuleColumnPK pk = new RuleColumnPK();
	
	@Column(name="COLUMNNUMBER")
	private Integer number;
	
	public RuleColumn() {}
	
	public RuleColumnPK getPk() {
		return this.pk;
	}
	
	public void setPk(RuleColumnPK p) {
		this.pk = p;
	}
	
	public Rule getRule() {
		return getPk().getRule();
	}
	
	public void setRule(Rule r) {
		getPk().setRule(r);
	}
	
	public AppColumn getColumn() {
		return getPk().getColumn();
	}
	
	public void setColumn(AppColumn c) {
		getPk().setColumn(c);
	}
	
	public Integer getNumber() {
		return this.number;
	}
	
	public void setNumber(Integer n) {
		this.number = n;
	}
	

}
