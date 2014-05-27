package es.deusto.otp.data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
public class User {
	protected String nick;
	protected String password;
	protected String email;
	
	public User(String nick, String email) {
		this.nick = nick;
		this.email = email;
	}
	
	public User(String id, String nick, String email) {
		this.nick = nick;
		this.email = email;
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
