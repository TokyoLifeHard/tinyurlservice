package ru.petprojects.tinyurlservice.entity;

public enum EncodedAlgorithm {
    MD5("MD5"),Base64("Base64");
    String algorithmName;

    EncodedAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
