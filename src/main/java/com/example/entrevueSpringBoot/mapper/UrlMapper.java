package com.example.entrevueSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.entrevueSpringBoot.dto.request.UrlPostRequest;
import com.example.entrevueSpringBoot.dto.response.UrlGetResponse;
import com.example.entrevueSpringBoot.dto.response.UrlPostResponse;
import com.example.entrevueSpringBoot.model.Url;

/*
 * @author Allan Martins
 */

@Mapper(componentModel = "spring") 
public interface UrlMapper {
   public String mapToUrlShortened(Url url);
    
   @Mapping(source = "request.urlToShort", target = "urlOriginal")
   public Url mapToUrl(UrlPostRequest request, String urlShortned);
    
   public UrlGetResponse mapToUrlGetResponse(Url url);  
   
   public UrlPostResponse mapToUrlPostResponse(Url url);
}
