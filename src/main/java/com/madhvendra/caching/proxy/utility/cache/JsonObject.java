package com.madhvendra.caching.proxy.utility.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class JsonObject {
    // here the actual json Object will be stored
    String jsonrespose;
    ObjectMapper objectMapper = new ObjectMapper();

    public String convertToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return "Error converting object to JSON: " + e.getMessage();
        }
    }

    public String getJsonResponse() {
        return jsonrespose;
    }

    public void setJsonResponse(Object object) {
        this.jsonrespose = convertToJsonString(object);
    }
}
