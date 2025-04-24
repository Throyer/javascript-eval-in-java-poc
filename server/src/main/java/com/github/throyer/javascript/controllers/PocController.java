package com.github.throyer.javascript.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.throyer.javascript.models.Parameters;
import com.github.throyer.javascript.services.JavascriptEngineService;
import com.github.throyer.javascript.utils.JavascriptUtils;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/poc")
@AllArgsConstructor
public class PocController {
  private final JavascriptEngineService service;

  @PostMapping
  public Object poc(@RequestBody Parameters parameters) {      
    return service.eligible(parameters);
  }

  @ApiResponse(
    responseCode = "200",
    description = "exemplo de função eligible.",
    content = {@Content(schema = @Schema(example = JavascriptUtils.FUNCTION_EXAMPLE))}
  )
  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String index() {
    return JavascriptUtils.func();
  }
}
