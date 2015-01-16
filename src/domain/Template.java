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
@Table(name = "TEMPLATES")
public class Template {
	@Id
	@GeneratedValue
	@Column(name = "TEMPLATE_ID")
	private int id;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATABASETYPE")
	private String databaseType;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RULETYPE_ID")
	private RuleType ruletype;

	public Template() {	}

	public Template(String databaseType, String name, String code, int id) {
		setDatabaseType(databaseType);
		setName(name);
		setCode(code);
		setId(id);
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
