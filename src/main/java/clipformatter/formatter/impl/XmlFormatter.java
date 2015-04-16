package clipformatter.formatter.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import clipformatter.formatter.Formatter;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

public class XmlFormatter implements Formatter {

	private static final int indent = 4;

	String xml;

	public XmlFormatter(String xml) {
		this.xml = xml;
	}

	@Override
	public String format() {

		String formatStr = "";
		try {

			formatStr = indent(xml, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(formatStr);
		return formatStr;
	}

    public static String indent(String xml, int indent) throws Exception {
        Transformer f = TransformerFactory.newInstance().newTransformer();
        f.setOutputProperty(OutputKeys.INDENT, "yes");
        f.setOutputProperty(OutputKeys.METHOD, "xml");
        f.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        f.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, String.valueOf(indent));
        byte[] bytes = xml.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        StreamSource src = new StreamSource(is);
        OutputStream os = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(os);
        f.transform(src, result);
        return os.toString();
    }
}
