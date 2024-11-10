package ru.petprojects.tinyurlservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import ru.petprojects.tinyurlservice.dto.UrlEncodeResponse;
import ru.petprojects.tinyurlservice.entity.Url;
import ru.petprojects.tinyurlservice.exception.TinyUrlAlreadyExistsException;
import ru.petprojects.tinyurlservice.repository.UrlDAO;

import java.time.LocalDateTime;

@Service
public class UrlEncodeService {

    private final Logger logger = LoggerFactory.getLogger(UrlEncodeService.class);

    private UrlCoder urlCoder;
    private final UrlDAO urlDAO;

    public UrlEncodeService(UrlCoder urlCoder, UrlDAO urlDAO, BeanFactory beanFactory) {
        this.urlCoder = urlCoder;
        this.urlDAO = urlDAO;
    }

    public UrlEncodeResponse encodeOriginUrl(String originUrl){
        Url url = new Url();
        Integer countOfOriginUrl = this.urlDAO.getCountOfOriginUrl(originUrl);
        String encodedUrl;


        logger.info("Origin url:{}",originUrl.equals(""));

        if (countOfOriginUrl.intValue() == 0){

            encodedUrl = this.urlCoder.code(originUrl);
            url.setOriginUrl(originUrl);
            url.setTinyUrl(encodedUrl);
            url.setCreationTime(LocalDateTime.now());
            urlDAO.createUrl(url);

        }else {
            throw new TinyUrlAlreadyExistsException(originUrl);
        }


        return new UrlEncodeResponse(createTinyUrl(encodedUrl));
    }

    private String createTinyUrl(String encodedUrl){
        return new StringBuilder("https://")
                .append(encodedUrl)
                .append(".gg")
                .toString();
    }

}
