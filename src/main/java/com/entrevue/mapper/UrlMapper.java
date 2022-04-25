package com.entrevue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;
import com.entrevue.model.Url;
import org.mapstruct.Mappings;

/*
 * @author Allan Martins
 */

@Mapper(componentModel = "spring") 
public interface UrlMapper {
   @Mappings({@Mapping(target = "urlOriginal", source = "request.urlToShort")})
   Url mapToUrl(UrlPostRequest request, String urlShortened);

   UrlGetResponse mapToUrlGetResponse(Url url);

   UrlPostResponse mapToUrlPostResponse(Url url);
}
