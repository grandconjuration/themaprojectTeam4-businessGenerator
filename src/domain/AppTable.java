package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABLES")
public class AppTable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "APPLICATION_ID", nullable = false)
	private Application application;

	// private int numberOfRules;

	public AppTable() {
	}

	public AppTable(String name, String code, int id, Application application,
			int numberOfRules) {
		setName(name);
		setCode(code);
		setId(id);
		setApplication(application);
		// setNumberOfRules(numberOfRules);
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	/*
	 * public int getNumberOfRules() { return numberOfRules; }
	 * 
	 * public void setNumberOfRules(int numberOfRules) { this.numberOfRules =
	 * numberOfRules; }
	 */

}
