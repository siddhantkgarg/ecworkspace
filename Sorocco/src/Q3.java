import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q3 {
	//123-456-1234
	//(123) 456-7890
	public static String find_phone_number(String text) {
		Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
		Pattern pattern2 = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}-\\d{4}");
		Matcher matcher = pattern.matcher(text);
		Matcher matcher2 = pattern2.matcher(text);
		
		if (matcher.find() ) {
		    return (matcher.group(0));
		}else if(matcher2.find()) {
			return (matcher2.group(0));
		}
		return "NONE";
	}
	public static void main(String s[]) {
		
		String[] str = {"000 000-0000","123-456-7890","1234567890","xxx999-999-9999","xxx(000) 000-0000111","This is 1 test 123-456-7890"};

		for (String string : str) {
			System.out.println(find_phone_number(string));
		}
		
	}
}
