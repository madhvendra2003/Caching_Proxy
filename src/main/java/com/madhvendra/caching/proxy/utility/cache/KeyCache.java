package com.madhvendra.caching.proxy.utility.cache;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + (urlpath == null ? 0 : urlpath.hashCode());
        result = 31 * result + (urlMethod == null ? 0 : urlMethod.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        KeyCache other = (KeyCache) obj;
        return Objects.equals(urlpath, other.urlpath) && Objects.equals(urlMethod, other.urlMethod);

    }

}
