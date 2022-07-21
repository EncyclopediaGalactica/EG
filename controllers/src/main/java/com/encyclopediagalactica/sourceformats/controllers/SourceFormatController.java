package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.services.sourceformat.services.sourceformat.AddServiceInterface;
import com.encyclopediagalactica.services.sourceformat.services.sourceformat.GetAllServiceInterface;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceFormatController {

  private final GetAllServiceInterface getAllService;
  private final AddServiceInterface addService;

  public SourceFormatController(
      @NonNull GetAllServiceInterface getAllService,
      @NonNull AddServiceInterface addService) {
    this.getAllService = getAllService;
    this.addService = addService;
  }

  @GetMapping("/sourceformat")
  public List<SourceFormatDto> getAll() {
    return getAllService.getAll();
  }

  @PostMapping("/sourceformat")
  public SourceFormatDto add(@RequestBody SourceFormatDto dto) {
    return addService.add(dto);
  }
}
