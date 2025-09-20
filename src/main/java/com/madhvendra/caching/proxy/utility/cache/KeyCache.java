package com.madhvendra.caching.proxy.utility.cache;


import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Getter
@Setter
@Component
public class KeyCache {
    String urlpath;
    String urlMethod;

    public KeyCache(String urlpath, String urlMethod) {
        this.urlpath = urlpath;
        this.urlMethod = urlMethod;
    }

}
