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
   public String mapToUrlShortened(Url url);
    
   @Mapping(source = "request.urlToShort", target = "urlOriginal")
   public Url mapToUrl(UrlPostRequest request, String urlShortened);
    
   public UrlGetResponse mapToUrlGetResponse(Url url);  
   
   public UrlPostResponse mapToUrlPostResponse(Url url);
}
