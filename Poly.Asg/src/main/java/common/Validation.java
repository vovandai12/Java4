package common;

import java.util.regex.Pattern;

public class Validation {

	public static void validateEmpty(String text, StringBuilder sb, String errorMessage) {
		if (text == null) {
			sb.append(errorMessage);
		}
	}

	public static void validateMinLenght(String text, int lenght, StringBuilder sb, String errorMessage) {
		if (text != null) {
			if (text.length() < lenght) {
				sb.append(errorMessage);
			}
		}
	}

	public static void validateEmail(String text, StringBuilder sb, String errorMessage) {
		if (text != null) {
			String emailPattern = "^[_A-Za-z0-9-\\\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(emailPattern);
			if (!pattern.matcher(text).find()) {
				sb.append(errorMessage);
			}
		}
	}
}
