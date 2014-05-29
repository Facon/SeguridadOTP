package es.deusto.otp.data;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="OTPCODE")
public class OTPCode {
	public static final String USER = "user";
	public static final String DATE = "date";
	public static final String CODE = "code";
	
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private User user;
	@DatabaseField
	private Date date = Calendar.getInstance().getTime();
	@DatabaseField
	private String code;
	
	public OTPCode() {
		
	}
	
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
	
	public Date getDate() {
		return date;
	}

	public void setCode(Date date) {
		this.date = date;
	}

	public static void main(String[] args) {
		OTPCode otp = new OTPCode(new User("Facon", "a@a.com"));
		System.out.println(otp.getCode());
	}
}
