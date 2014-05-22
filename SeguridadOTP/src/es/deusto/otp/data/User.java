package es.deusto.otp.data;

import java.util.UUID;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable(detachable="true")
public class User {
	@PrimaryKey
	protected String id;
	@Unique
	protected String nick;
	protected String password;
	protected String email;
	
	public User(String nick, String email) {
		this.id = UUID.randomUUID().toString();
		this.nick = nick;
		this.email = email;
	}
	
	public User(String id, String nick, String email) {
		this.id = id;
		this.nick = nick;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setIp(String email) {
		this.email = email;
	}
}
