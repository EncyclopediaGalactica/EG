package com.encyclopediagalactica.sourceformats.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.encyclopediagalactica.sourceformats")
public class SourceFormatServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SourceFormatServiceApplication.class, args);
  }

}
