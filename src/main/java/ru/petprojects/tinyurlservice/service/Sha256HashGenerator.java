package ru.petprojects.tinyurlservice.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Sha256HashGenerator implements HashGenerator{
    @Override
    public String generateHash(String originUrl) {
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md5.update(originUrl.getBytes());

        return new BigInteger(1, md5.digest()).toString(16);
    }
}
