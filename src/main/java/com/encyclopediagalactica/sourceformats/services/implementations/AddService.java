package com.encyclopediagalactica.sourceformats.services.implementations;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AddService implements AddServiceInterface {

  private final SourceFormatRepository repository;
  private final SourceFormatMapperInterface mapper;

  public AddService(
      @NonNull SourceFormatRepository repository,
      @NonNull SourceFormatMapperInterface mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SourceFormatDto add(
      @NonNull @Valid SourceFormatDto dto) {
    
    this.trimDtoStringProperties(dto);
    this.validateInputDto(dto);
    SourceFormat sourceFormat = mapper.mapSourceFormatDtoToSourceFormat(dto);
    
    this.trimEntityStringProperties(sourceFormat);
    this.validateEntity(sourceFormat);
    SourceFormat result = repository.save(sourceFormat);
    SourceFormatDto resultDto = mapper.mapSourceFormatToSourceFormatDto(result);
    
    return resultDto;
  }
  
  private void trimDtoStringProperties(SourceFormatDto dto) {
    if(dto.getName() != null) {
      dto.setName(dto.getName().trim());
    }
  }
  
  private void validateInputDto(SourceFormatDto dto) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<SourceFormatDto>> violations = validator.validate(dto);
    if(!violations.isEmpty()){
      throw new ConstraintViolationException(violations);
    }
  }
  
  private void trimEntityStringProperties(SourceFormat sf) {
    if(sf.getName() != null) {
      sf.setName(sf.getName().trim());
    }
  }
  
  private void validateEntity(SourceFormat sourceFormat) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<SourceFormat>> violations = validator.validate(sourceFormat);
    if(!violations.isEmpty()){
      throw new ConstraintViolationException(violations);
    }
  }
}
