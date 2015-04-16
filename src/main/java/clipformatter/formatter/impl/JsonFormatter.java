package clipformatter.formatter.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import clipformatter.formatter.Formatter;

public class JsonFormatter implements Formatter {

	String value;

	public JsonFormatter(String value) {
		this.value = value;
	}

	@Override
	public String format() {
		JsonObject jsonObject;

		try (JsonReader jsonReader = Json.createReader(new StringReader(value))) {
			jsonObject = jsonReader.readObject();
		}

		Map<String, Object> properties = new HashMap<>(1);
		properties.put(JsonGenerator.PRETTY_PRINTING, true);

		StringWriter sw = new StringWriter();
		JsonWriterFactory jwf = Json.createWriterFactory(properties);

		try (JsonWriter jsonWriter = jwf.createWriter(sw)) {
			jsonWriter.writeObject(jsonObject);
		}

		String formatStr = sw.toString().trim();

		System.out.println(formatStr);

		return formatStr;
	}

}
