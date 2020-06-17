package date;

import java.util.regex.Pattern;

public class date {
	public static boolean Isint(String need) {
		Pattern intPattern=Pattern.compile("^[-\\+]?[\\d]*$");
		Boolean intFlag=intPattern.matcher(need).matches();
		return intFlag;
	}
}
