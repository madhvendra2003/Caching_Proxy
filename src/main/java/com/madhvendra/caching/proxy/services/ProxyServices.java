package com.madhvendra.caching.proxy.services;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor

public class ProxyServices {
    public Optional<Object> getResponse(String reqUrl, String reqMethod) {
        return null;
    }


    // here the services will be implemented and will be called in  controller

}
