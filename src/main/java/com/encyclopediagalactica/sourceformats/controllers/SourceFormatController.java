package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sourceformat")
public class SourceFormatController {

  private final GetAllServiceInterface getAllService;
  private final AddServiceInterface addService;

  public SourceFormatController(
      @NonNull GetAllServiceInterface getAllService,
      @NonNull  AddServiceInterface addService) {
    this.getAllService = getAllService;
    this.addService = addService;
  }

  @GetMapping(
      value = "/getall")
  @ResponseBody
  public List<SourceFormatDto> getAll() {
    return getAllService.getAll();
  }

  @PostMapping(
      consumes = "application/json"
  )
  @ResponseBody
  public ResponseEntity<SourceFormatDto> add(@RequestBody SourceFormatDto dto) {
    
    SourceFormatDto result = addService.add(dto);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }
}
