package com.madhvendra.caching.proxy.services;

import com.madhvendra.caching.proxy.utility.Constants;
import com.madhvendra.caching.proxy.utility.cache.GuavaCache;
import com.madhvendra.caching.proxy.utility.cache.KeyCache;
import com.madhvendra.caching.proxy.utility.restclient.RestCall;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor

public class ProxyServices {


    String reqUrl;
    String reqMethod;

    @Autowired
    GuavaCache guavaCache = new GuavaCache() ;

    @Autowired
    RestCall restCall = new RestCall();




    public Optional<Object> getResponse(String reqUrl, String reqMethod) {
        this.reqUrl = reqUrl;
        this.reqMethod = reqMethod;

        KeyCache keyCache = new KeyCache(reqUrl, reqMethod);
        Object cachedResponse = guavaCache.get(keyCache);
        if (cachedResponse != null) {
            System.out.println("Cache hit for key: " + keyCache);
            return Optional.of(cachedResponse);
        } else {
            System.out.println("Cache miss for key: " + keyCache);
            restCall.setOriginUrl(Constants.ORIGIN_URL);
            restCall.setURL(reqUrl);
            restCall.setMethod(reqMethod);
            Object response = restCall.getResponse().getJsonResponse();
            System.out.println(response);
            guavaCache.put(keyCache, restCall.getResponse());
            return Optional.ofNullable(response);
        }
    }


    // here the services will be implemented and will be called in  controller

}
