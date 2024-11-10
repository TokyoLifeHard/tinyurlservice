package ru.petprojects.tinyurlservice.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Primary
public class Base64UrlCoder implements UrlCoder{
    private HashGenerator hashGenerator;

    public Base64UrlCoder(HashGenerator hashGenerator) {
        this.hashGenerator = hashGenerator;
    }

    @Override
    public String code(String originUrl) {
        String generatedHash = this.hashGenerator.generateHash(originUrl);

        return Base64.getUrlEncoder().encodeToString(generatedHash.getBytes(StandardCharsets.UTF_8)).substring(0,6);
    }
}
