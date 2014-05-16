package es.deusto.otp.data;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.codec.digest.DigestUtils;

@PersistenceCapable(detachable="true")
public class OTPdata {
	@PrimaryKey
	private User user;
	@PrimaryKey
	private Date date = Calendar.getInstance().getTime();
	private String code;
	
	public OTPdata(User user){
		this.user = user;
		
		String digest = DigestUtils.sha1Hex(date.toString());
		
		int offset = 13;
		
		setCode(digest.substring(0 + offset, 5 + offset));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void main(String[] args) {
		new OTPdata(new User("Facon", "a@a.com"));
	}
}
