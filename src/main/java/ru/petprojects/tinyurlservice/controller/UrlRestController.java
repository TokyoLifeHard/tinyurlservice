package ru.petprojects.tinyurlservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.petprojects.tinyurlservice.dto.UrlEncodeRequest;
import ru.petprojects.tinyurlservice.dto.UrlEncodeResponse;
import ru.petprojects.tinyurlservice.exception.EmptyOriginUrlException;
import ru.petprojects.tinyurlservice.service.RedirectService;
import ru.petprojects.tinyurlservice.service.UrlEncodeService;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Controller
@RequestMapping("/v1/api/url")
public class UrlRestController {

    @Autowired
    private Environment environment;

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlRestController.class);
    UrlEncodeService urlEncodeService;
    RedirectService redirectService;

    public UrlRestController(UrlEncodeService urlEncodeService,RedirectService redirectService) {
        this.urlEncodeService = urlEncodeService;
        this.redirectService = redirectService;
    }

    @PostMapping(path = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UrlEncodeResponse encodeUrl(@RequestBody UrlEncodeRequest urlEncodeRequest) throws UnknownHostException {

        if (urlEncodeRequest.originUrl().equals("")) {
            throw new EmptyOriginUrlException();
        }
        return urlEncodeService.encodeOriginUrl(urlEncodeRequest.originUrl());
    }

    @GetMapping(path = "/redirect/{tinyUrl}")
    public String redirect(@PathVariable("tinyUrl") String tinyUrl){
        return "redirect:".concat(this.redirectService.getOriginUrlByTinyUrl(tinyUrl));
    }
}
