package com.madhvendra.caching.proxy.utility.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.NoArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Component

public class GuavaCache {

    
    // here the actual implementation of the guava cache will be done
    // it will be called in services
    private Cache <KeyCache, JsonObject> cache;

    public GuavaCache(){
        cache = CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
        .build(
                new CacheLoader<KeyCache, JsonObject>() {
                    @Override
                    public JsonObject load(KeyCache key) throws Exception {

                        System.out.println("Cache miss for key: " + key);
                        throw new Exception("No value found for key: " + key);
                    }
                }
        );
    }

    public JsonObject get(KeyCache key){
        return cache.getIfPresent(key);
    }

    public void put (KeyCache key, JsonObject value){
        cache.put(key, value);

    }

    public void clearAll() {
        cache.invalidateAll();
    }


}
