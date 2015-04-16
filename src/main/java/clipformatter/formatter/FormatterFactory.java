package clipformatter.formatter;

import clipformatter.formatter.impl.JsonArrayFormatter;
import clipformatter.formatter.impl.JsonFormatter;
import clipformatter.formatter.impl.XmlFormatter;

public class FormatterFactory {

	public static Formatter getFormatter(String value) {

		String trimString = value.trim().replaceAll("\t", " ")
				.replaceAll("\r\n", " ").replaceAll("\n", " ");

		System.out.println(trimString);

		String startStr = trimString.trim().substring(0, 1);

		if ("{".equals(startStr)) {
			trimString = trimString.replaceAll(" ", "");
			return new JsonFormatter(trimString);
		} else if ("[".equals(startStr)) {
			trimString = trimString.replaceAll(" ", "");
			return new JsonArrayFormatter(trimString);
		} else if ("<".equals(startStr)) {
			return new XmlFormatter(trimString);
		} else {
			throw new RuntimeException("can't format string");
		}
	}
}
