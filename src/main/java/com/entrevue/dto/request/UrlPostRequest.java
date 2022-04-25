package com.entrevue.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlPostRequest {
	
   @NotBlank(message = "urlToShort cannot be null or empty")
   @Schema(required = true, description = "Url to be shortened")
   private String urlToShort;
}
