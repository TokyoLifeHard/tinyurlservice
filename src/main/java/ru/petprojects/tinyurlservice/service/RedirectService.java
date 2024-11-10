package ru.petprojects.tinyurlservice.service;

import org.springframework.stereotype.Service;
import ru.petprojects.tinyurlservice.entity.Url;
import ru.petprojects.tinyurlservice.repository.UrlDAO;

@Service
public class RedirectService {

    private final UrlDAO redierctServiceUrlDao;

    public RedirectService(UrlDAO redierctServiceUrlDao) {
        this.redierctServiceUrlDao = redierctServiceUrlDao;
    }

    public String getOriginUrlByTinyUrl(String tinyUrl){
        Url urlByTinyUrl = redierctServiceUrlDao.getUrlByTinyUrl(tinyUrl);
        return urlByTinyUrl.getOriginUrl();
    }
}
