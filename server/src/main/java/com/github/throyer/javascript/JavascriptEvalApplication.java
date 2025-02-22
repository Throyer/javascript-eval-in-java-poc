package com.github.throyer.javascript;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavascriptEvalApplication {
  public static void main(String[] args) {
    System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
    run(JavascriptEvalApplication.class, args);
  }
}
