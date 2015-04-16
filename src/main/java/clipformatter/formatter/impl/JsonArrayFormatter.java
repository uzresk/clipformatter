package clipformatter.formatter.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import clipformatter.formatter.Formatter;

public class JsonArrayFormatter implements Formatter {

	private String value;

	public JsonArrayFormatter(String value) {
		this.value = value;
	}

	@Override
	public String format() {
		JsonArray jsonArray;

		try (JsonReader jsonReader = Json.createReader(new StringReader(value))) {
			jsonArray = jsonReader.readArray();
		}

		Map<String, Object> properties = new HashMap<>(1);
		properties.put(JsonGenerator.PRETTY_PRINTING, true);

		StringWriter sw = new StringWriter();
		JsonWriterFactory jwf = Json.createWriterFactory(properties);

		try (JsonWriter jsonWriter = jwf.createWriter(sw)) {
			jsonWriter.writeArray(jsonArray);
		}

		String formatStr = sw.toString().trim();

		System.out.println(formatStr);

		return formatStr;
	}

}
