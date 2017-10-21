package com.despi.jwtrestserver.domain.jsonb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JacksonUtil {

	public static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        OBJECT_MAPPER.findAndRegisterModules();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	public static <T> T fromString(String string, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(string, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("Can't convert json to " + clazz + ":" + string, e);
		}
	}

	public static String toString(Object value) {
		try {
			return OBJECT_MAPPER.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Can't convert to json: "
					+ value, e);
		}
	}

	public static JsonNode toJsonNode(Object object) {
		return OBJECT_MAPPER.valueToTree(object);
	}

	public static ObjectNode toObjectNode(Object object) {
		return OBJECT_MAPPER.valueToTree(object);
	}

	public static ObjectNode toObjectNode(String json) {
		return (ObjectNode)toJsonNode(json);
	}

	public static JsonNode toJsonNode(String json) {
		try {
			return OBJECT_MAPPER.readTree(json);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T clone(T value) {
		return fromString(toString(value), (Class<T>) value.getClass());
	}

	public static <T> T fromByteArray(byte[] string, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(string, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("Can't convert json to " + clazz + ":" + string, e);
		}
	}

	public static byte[] toByteArray(Object value) {
		try {
			return OBJECT_MAPPER.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Can't convert to json: "
					+ value, e);
		}
	}
}
