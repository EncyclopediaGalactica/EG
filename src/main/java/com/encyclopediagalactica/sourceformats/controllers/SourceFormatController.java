package com.encyclopediagalactica.sourceformats.controllers;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.DeleteByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.FindByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.GetAllServiceInterface;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceInterface;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceFormatController {

  private final GetAllServiceInterface getAllService;
  private final AddServiceInterface addService;
  private final DeleteByIdServiceInterface deleteByIdService;
  private final UpdateServiceInterface updateService;

  private final FindByIdServiceInterface findByIdService;

  public SourceFormatController(
      @NonNull GetAllServiceInterface getAllService,
      @NonNull AddServiceInterface addService,
      @NonNull DeleteByIdServiceInterface deleteByIdService,
      @NonNull FindByIdServiceInterface findByIdService,
      @NonNull UpdateServiceInterface updateService) {
    this.getAllService = getAllService;
    this.addService = addService;
    this.deleteByIdService = deleteByIdService;
    this.findByIdService = findByIdService;
    this.updateService = updateService;
  }

  @GetMapping(
      value = "/sourceformats")
  @ResponseBody
  public List<SourceFormatDto> getAll() {
    return getAllService.getAll();
  }

  @GetMapping(
      value = "/sourceformats/{id}"
  )
  @ResponseBody
  public ResponseEntity<SourceFormatDto> getById(@PathVariable long id) {
    SourceFormatDto result = this.findByIdService.findById(id);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping(
      value = "/sourceformats/{id}",
      consumes = "application/json"
  )
  @ResponseBody
  public ResponseEntity<SourceFormatDto> update(@PathVariable long id, @RequestBody SourceFormatDto dto) {
    SourceFormatDto result = updateService.updateById(dto);
    return new ResponseEntity<>(result, HttpStatus.OK);
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
