package es.deusto.otp.data;

import java.util.UUID;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Client {
	@PrimaryKey
	protected String id;
	protected String nick;
	protected String ip;
	
	public Client(String nick, String ip) {
		this.id = UUID.randomUUID().toString();
		this.nick = nick;
		this.ip = ip;
	}
	
	public Client(String id, String nick, String ip) {
		this.id = id;
		this.nick = nick;
		this.ip = ip;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
