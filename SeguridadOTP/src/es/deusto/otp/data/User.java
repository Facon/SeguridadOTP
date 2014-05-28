package es.deusto.otp.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="USERS")
public class User {
	public static final String NICK = "nick";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	
	@DatabaseField(id=true)
	protected String nick;
	@DatabaseField
	protected String password;
	@DatabaseField
	protected String email;
	
	public User() {
		
	}
	
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
