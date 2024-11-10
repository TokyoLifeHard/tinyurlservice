package ru.petprojects.tinyurlservice.repository;


import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.petprojects.tinyurlservice.entity.Url;

@Repository
@Scope("prototype")
public class UrlDAO {

    private final NamedParameterJdbcTemplate template;

    public UrlDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }


    public Long createUrl(Url url){

        String createQuery = "insert into urls (origin_url,tiny_url,creation_time) values (:origin_url,:tiny_url,:creation_time) returning id";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("origin_url",url.getOriginUrl())
                .addValue("tiny_url", url.getTinyUrl())
                .addValue("creation_time",url.getCreationTime());

        return this.template.queryForObject(createQuery,sqlParameterSource,Long.class);
    }

    public Url getUrlById(long id){
        String getUrlByIdQuery = "select * from urls where url.id=:id";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("id",id);
        return template.queryForObject(getUrlByIdQuery,sqlParameterSource,new UrlRowMapper());
    }

    public Url getUrlByTinyUrl(String tinyUrl){
        String getUrlByTinyUrlQuery = "select * from urls where urls.tiny_url=:tiny_url";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("tiny_url",tinyUrl);
        return template.queryForObject(getUrlByTinyUrlQuery,sqlParameterSource,new UrlRowMapper());
    }

    public Url getUrlByOriginUrl(String originUrl){
        String getUrlByIdQuery = "select * from urls where urls.origin_url=:origin_url";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("origin_url",originUrl);
        return template.queryForObject(getUrlByIdQuery, sqlParameterSource, new UrlRowMapper());
    }

    public Integer getCountOfOriginUrl(String originUrl){
        String sqlCountOriginUrls = "SELECT COUNT(id) FROM urls WHERE urls.origin_url =:origin_url;";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("origin_url",originUrl);
        return template.queryForObject(sqlCountOriginUrls,sqlParameterSource,Integer.class);
    }
}
