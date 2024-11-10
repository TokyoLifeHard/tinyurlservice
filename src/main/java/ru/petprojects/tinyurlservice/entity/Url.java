package ru.petprojects.tinyurlservice.entity;


import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Url {
    Long id;
    String originUrl;
    String tinyUrl;
    LocalDateTime creationTime;
}
