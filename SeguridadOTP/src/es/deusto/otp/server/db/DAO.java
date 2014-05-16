package es.deusto.otp.server.db;

import es.deusto.otp.data.User;

public interface DAO {
	public void addUser(User user);
	public User getUser(String nick);
}
