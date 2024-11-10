package ru.petprojects.tinyurlservice.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.petprojects.tinyurlservice.entity.Url;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlRowMapper implements RowMapper<Url> {
    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getLong("id"));
        url.setOriginUrl(rs.getString("origin_url"));
        url.setTinyUrl(rs.getString("tiny_url"));
        url.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
        return url;
    }
}
