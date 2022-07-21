package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.services.sourceformat.services.sourceformat.GetAllServiceInterface;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceFormatController {

  private final GetAllServiceInterface getAllService;

  public SourceFormatController(
      @NonNull GetAllServiceInterface getAllService) {
    this.getAllService = getAllService;
  }

  @GetMapping("/sourceformat")
  public List<SourceFormatDto> getAll() {
    return getAllService.getAll();
  }
}
