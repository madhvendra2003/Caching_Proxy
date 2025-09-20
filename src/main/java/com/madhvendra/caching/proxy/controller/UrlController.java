package com.madhvendra.caching.proxy.controller;

import com.madhvendra.caching.proxy.services.ProxyServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class UrlController {
    // here the endpoints will be defined
    ProxyServices proxyServices;
    public UrlController(ProxyServices proxyServices) {
        this.proxyServices = proxyServices;
    }


    @RequestMapping(path = "/*")
    public ResponseEntity<?> url (HttpServletRequest request) throws IOException {
      String reqUrl  = request.getRequestURI();
      String reqMethod = request.getMethod();


        Optional<Object> data  = proxyServices.getResponse(reqUrl, reqMethod);
        return data.<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
