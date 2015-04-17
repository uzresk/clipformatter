package prettyprint.formatter;

import java.util.Arrays;

import prettyprint.formatter.impl.JsonArrayFormatter;
import prettyprint.formatter.impl.JsonFormatter;
import prettyprint.formatter.impl.SQLFormatter;
import prettyprint.formatter.impl.XmlFormatter;

public class FormatterFactory {

	public static Formatter getFormatter(String value)
			throws FormatterException {

		// trim tab, crlf
		String trimString = value.trim().replaceAll("\t", " ")
				.replaceAll("\r\n", " ").replaceAll("\n", " ");

		String startStr = trimString.trim().substring(0, 1);

		if ("{".equals(startStr)) {
//			trimString = trimString.replaceAll(" ", "");
			return new JsonFormatter(trimString);
		} else if ("[".equals(startStr)) {
//			trimString = trimString.replaceAll(" ", "");
			return new JsonArrayFormatter(trimString);
		} else if ("<".equals(startStr)) {
			return new XmlFormatter(trimString);
		} else if (isSQL(trimString)) {
			return new SQLFormatter(trimString);
		} else {
			throw new FormatterException("can't format.[" + value + "]");
		}
	}

	private static final String[] sqlPrefix = { "select", "insert", "update",
			"delete" };

	private static boolean isSQL(String value) {

		String lowerString = value.toLowerCase();

		return Arrays.asList(sqlPrefix).stream()
				.anyMatch(s -> lowerString.startsWith(s));
	}
}
