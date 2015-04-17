package prettyprint.formatter.impl;

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

import prettyprint.formatter.Formatter;
import prettyprint.formatter.FormatterException;

public class JsonArrayFormatter implements Formatter {

	private String json;

	public JsonArrayFormatter(String json) {
		this.json = json;
	}

	@Override
	public String format() throws FormatterException {
		JsonArray jsonArray;

		try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
			jsonArray = jsonReader.readArray();
		}

		Map<String, Object> properties = new HashMap<>(1);
		properties.put(JsonGenerator.PRETTY_PRINTING, true);

		StringWriter sw = new StringWriter();
		JsonWriterFactory jwf = Json.createWriterFactory(properties);

		try (JsonWriter jsonWriter = jwf.createWriter(sw)) {
			jsonWriter.writeArray(jsonArray);
		}

		return sw.toString().trim();

	}

}
