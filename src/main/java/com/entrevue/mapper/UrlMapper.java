package com.entrevue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;
import com.entrevue.model.Url;

/*
 * @author Allan Martins
 */

@Mapper(componentModel = "spring") 
public interface UrlMapper {
   @Mapping(source = "request.urlToShort", target = "urlOriginal")
   Url mapToUrl(UrlPostRequest request, String urlShortened);

   UrlGetResponse mapToUrlGetResponse(Url url);

   UrlPostResponse mapToUrlPostResponse(Url url);
}
