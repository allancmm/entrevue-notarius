package com.entrevue.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * @author Allan Martins
 */
@Data
@AllArgsConstructor
public class UrlGetResponse {
    private String urlOriginal;
    private String urlShortened;
}
