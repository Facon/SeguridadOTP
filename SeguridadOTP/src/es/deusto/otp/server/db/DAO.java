package es.deusto.otp.server.db;

import es.deusto.otp.data.OTPCode;
import es.deusto.otp.data.User;

public interface DAO {
	public void addUser(User user);
	public User getUser(String nick);
	public void addOTPCode(OTPCode code);
	public OTPCode getOTPCode(User user);
}
