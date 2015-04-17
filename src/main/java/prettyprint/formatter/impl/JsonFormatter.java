package prettyprint.formatter.impl;

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

import prettyprint.formatter.Formatter;
import prettyprint.formatter.FormatterException;

public class JsonFormatter implements Formatter {

	String json;

	public JsonFormatter(String json) {
		this.json = json;
	}

	@Override
	public String format() throws FormatterException {
		JsonObject jsonObject;

		try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
			jsonObject = jsonReader.readObject();
		}

		Map<String, Object> properties = new HashMap<>(1);
		properties.put(JsonGenerator.PRETTY_PRINTING, true);

		StringWriter sw = new StringWriter();
		JsonWriterFactory jwf = Json.createWriterFactory(properties);

		try (JsonWriter jsonWriter = jwf.createWriter(sw)) {
			jsonWriter.writeObject(jsonObject);
		}

		return sw.toString().trim();
	}

}
