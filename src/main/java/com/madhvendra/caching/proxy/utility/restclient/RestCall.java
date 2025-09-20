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

   public String originUrl = "https://dummyjson.com";
   public String URL ;

   public String method;

   public RestCall(String originUrl, String method , String URL){
         this.originUrl = originUrl;
         this.method = method;
         this.URL = URL;
   }

    public RestCall(String method , String URL){
        this.method = method;
        this.URL = URL;
    }


   RestTemplate restTemplate = new RestTemplate();

    public JsonObject getResponse(){
        JsonObject jsonObject = new JsonObject();
        System.out.println("Making "+method+" request to: "+originUrl+URL);

            Object response = restTemplate.getForEntity(originUrl+URL, Object.class);
            String result = response.toString();
            System.out.println("inside the class : " + result);
            jsonObject.setJsonResponse(result);

        return jsonObject;
    }


}
