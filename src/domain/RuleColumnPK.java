package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RuleColumnPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5014149942987152833L;
	
	@ManyToOne
	private Rule rule;
	@ManyToOne
	private AppColumn column;
	
	public void setRule(Rule r) {
		this.rule = r;
	}
	
	public Rule getRule() {
		return this.rule;
	}
	
	public void setColumn(AppColumn c) {
		this.column = c;
	}
	
	public AppColumn getColumn() {
		return this.column;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        RuleColumnPK that = (RuleColumnPK) o;
 
        if (rule != null ? !rule.equals(rule) : that.rule != null) return false;
        if (column != null ? !column.equals(that.column) : that.column != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }

}
