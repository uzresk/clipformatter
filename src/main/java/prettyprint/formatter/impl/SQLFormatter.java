package prettyprint.formatter.impl;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

import prettyprint.formatter.Formatter;
import prettyprint.formatter.FormatterException;

public class SQLFormatter implements Formatter {

	String sql;

	public SQLFormatter(String sql) {
		this.sql = sql;
	}

	@Override
	public String format() throws FormatterException {
		String formatSQL = new BasicFormatterImpl().format(sql);
		return formatSQL;
	}
}
