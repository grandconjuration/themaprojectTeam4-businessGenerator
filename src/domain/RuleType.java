package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RULETYPE")
public class RuleType {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NUMBEROFVALUES")
	private int numberOfValues;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ruletype")
	private List<Template> allTemplates = new ArrayList<Template>();

	public RuleType() {

	}

	public RuleType(String code, String name, String description,
			int numberOfValues, int id, ArrayList<Template> allTemplates,
			ArrayList<Operator> allOperators) {
		setCode(code);
		setName(name);
		setDescription(description);
		setNumberOfValues(numberOfValues);
		setId(id);
		setAllTemplates(allTemplates);
		setAllOperators(allOperators);
	}

	public Template searchTemplate(String databaseType) {
		Template x = null;
		for (Template t : allTemplates) {
			if (t.getDatabaseType().equals(databaseType)) {
				x = t;
			}
		}
		return x;
	}

	public void addTemplate(Template template) {
		allTemplates.add(template);
	}

	public void addOperator(Operator operator) {
	//	allOperators.add(operator);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfValues() {
		return numberOfValues;
	}

	public void setNumberOfValues(int numberOfValues) {
		this.numberOfValues = numberOfValues;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Template> getAllTemplates() {
		return allTemplates;
	}

	public void setAllTemplates(ArrayList<Template> allTemplates) {
		this.allTemplates = allTemplates;
	}

/*	public List<Operator> getAllOperators() {
		return allOperators;
	}*/

	public void setAllOperators(ArrayList<Operator> allOperators) {
//		this.allOperators = allOperators;
	}
}