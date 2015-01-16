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
@Table(name = "COLUMNS")
public class AppColumn {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TYPE")
	private String type;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TABLE_ID", nullable = false)
	private AppTable table;
	
	public AppColumn()	{}
	
	public AppColumn(String name, String type, int id, AppTable tabel){
		setName(name);
		setType(type);
		setId(id);
		setTable(tabel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AppTable getTable() {
		return table;
	}

	public void setTable(AppTable table) {
		this.table = table;
	}
}
