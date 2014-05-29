package es.deusto.otp.protocol;

import java.util.List;

public interface Protocol {
	public String processInput(String theInput);
	public String getCode(int code, List<String> list);
}
