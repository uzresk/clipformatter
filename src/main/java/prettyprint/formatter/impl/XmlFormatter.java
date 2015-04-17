package prettyprint.formatter.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import prettyprint.formatter.Formatter;
import prettyprint.formatter.FormatterException;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

@SuppressWarnings("restriction")
public class XmlFormatter implements Formatter {

	private static final int INDENT = 4;

	String xml;

	public XmlFormatter(String xml) {
		this.xml = xml;
	}

	@Override
	public String format() throws FormatterException {

		// trim whitespace
		xml = xml.replaceAll(">\\s*<", "><");

		System.out.println(xml);

		String formatStr = "";
		try {

			formatStr = indent(xml, INDENT);
		} catch (Throwable e) {
			throw new FormatterException("Invalid XML Format. [" + xml + "]");
		}
		return formatStr;
	}

	public static String indent(String xml, int indent) throws Exception {
		Transformer f = TransformerFactory.newInstance().newTransformer();
		f.setOutputProperty(OutputKeys.INDENT, "yes");
		f.setOutputProperty(OutputKeys.METHOD, "xml");
		f.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		f.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT,
				String.valueOf(indent));
		byte[] bytes = xml.getBytes();
		InputStream is = new ByteArrayInputStream(bytes);
		StreamSource src = new StreamSource(is);
		OutputStream os = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(os);
		f.transform(src, result);
		return os.toString();
	}
}
