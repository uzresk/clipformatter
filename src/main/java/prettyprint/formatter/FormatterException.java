package prettyprint.formatter;

public class FormatterException extends Exception {

	private static final long serialVersionUID = 1L;

	public FormatterException(String errorMessage) {
		super(errorMessage);
	}

	public FormatterException(String errorMessage, Throwable th) {
		super(errorMessage, th);
	}
}
