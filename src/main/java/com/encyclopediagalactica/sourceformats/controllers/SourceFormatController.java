package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.DeleteByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceFormatController {

  private final GetAllServiceInterface getAllService;
  private final AddServiceInterface addService;
  private final DeleteByIdServiceInterface deleteByIdService;

  public SourceFormatController(
      @NonNull GetAllServiceInterface getAllService,
      @NonNull AddServiceInterface addService,
      @NonNull DeleteByIdServiceInterface deleteByIdService) {
    this.getAllService = getAllService;
    this.addService = addService;
    this.deleteByIdService = deleteByIdService;
  }

  @GetMapping(
      value = "/sourceformats")
  @ResponseBody
  public List<SourceFormatDto> getAll() {
    return getAllService.getAll();
  }

  @PostMapping(
      value = "/sourceformats",
      consumes = "application/json"
  )
  @ResponseBody
  public ResponseEntity<SourceFormatDto> add(@RequestBody SourceFormatDto dto) {

    SourceFormatDto result = addService.add(dto);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @DeleteMapping(
      value = "/sourceformats/{sourceFormatId}"
  )
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable long sourceFormatId) {
    this.deleteByIdService.deleteById(sourceFormatId);
  }
}
