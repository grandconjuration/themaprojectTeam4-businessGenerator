package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "APPLICATION")
public class Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3675574929707664504L;
	 	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;
	
	@Column(name = "IPADRES")
	private String ipAdres;
	
	@Column(name = "USERNAME")	
	private String username;
	
	@Column(name = "PASSWORD")	
	private String password;
	
	@Column(name = "DATABASETYPE")
	private String databaseType;
	
	@Column(name = "PORT")
	private int port;
	
	public Application() {}
	
	public Application(String name, String code, String ipAdres, String username, String password){
		setName(name);
		setCode(code);
		setIpAdres(ipAdres);
		setUsername(username);
		setPassword(password);
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

	public String getIpAdres() {
		return ipAdres;
	}

	public void setIpAdres(String ipAdres) {
		this.ipAdres = ipAdres;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setDatabaseType(String type) {
		this.databaseType = type;
	}
	
	public String getDatabaseType() {
		return databaseType;
	}
	
	public void setPort(int portnr){
		this.port = portnr;
	}
	
	public int getPort(){
		return port;
	}
}