package es.deusto.otp.data;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.commons.codec.digest.DigestUtils;

@PersistenceCapable(detachable="true")
public class OTPCode {
	private User user;
	private Date date = Calendar.getInstance().getTime();
	private String code;
	
	public OTPCode(User user) {
		this.setUser(user);
		
		String digest = DigestUtils.sha1Hex(date.toString());
		
		int offset = 13;
		
		setCode(digest.substring(0 + offset, 5 + offset));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void main(String[] args) {
		OTPCode otp = new OTPCode(new User("Facon", "a@a.com"));
		System.out.println(otp.getCode());
	}
}
