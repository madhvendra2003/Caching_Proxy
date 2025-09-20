package com.madhvendra.caching.proxy.utility.restclient;


import com.madhvendra.caching.proxy.utility.cache.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
public class RestCall {
    // here the actual implementation of the rest client will be done
    // it will be called in services
   public String URL ;

   public String method;


   RestTemplate restTemplate = new RestTemplate();

    public JsonObject getResponse(){
        JsonObject jsonObject = new JsonObject();
        if(method.equals("GET")){
            Object response = restTemplate.getForObject(URL, Object.class);
            jsonObject.setJsonResponse(response);
        }
        return jsonObject;
    }


}
