package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import org.springframework.lang.NonNull;
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
      @NonNull  AddServiceInterface addService) {
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
