package ru.petprojects.tinyurlservice.dto;

import ru.petprojects.tinyurlservice.entity.EncodedAlgorithm;

public record UrlEncodeRequest(String originUrl, EncodedAlgorithm encodedAlgorithm) {
}
