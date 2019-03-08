package mx.tiendas3b.oauth2;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomAuthenticationException> {

	private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
		super(CustomAuthenticationException.class);
	}

	@Override
	public void serialize(CustomAuthenticationException value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeStringField("error", value.getOAuth2ErrorCode());
		gen.writeNumberField("status", 401);
		gen.writeStringField("custom_error_description", value.getMessage());
		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				gen.writeStringField(key, add);
			}
		}
		gen.writeEndObject();
	}

}
