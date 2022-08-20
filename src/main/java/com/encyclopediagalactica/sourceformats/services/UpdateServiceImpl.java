package com.encyclopediagalactica.sourceformats.services;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.entities.validation.SourceFormatUpdateValidationGroup;
import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl implements UpdateServiceInterface {

  private final Validator validator;
  private final SourceFormatMapperInterface mapper;
  private final SourceFormatRepository repository;

  public UpdateServiceImpl(
      @NonNull SourceFormatMapperInterface mapper,
      @NonNull SourceFormatRepository repository
  ) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public SourceFormatDto updateById(@NonNull SourceFormatDto dto) {
    trimDtoValues(dto);
    validateInput(dto);
    SourceFormat updateValues = mapper.mapSourceFormatDtoToSourceFormat(dto);
    validateInputSourceFormat(updateValues);
    SourceFormat originalSourceFormat = repository.findById(updateValues.getId()).orElseThrow();
    updateProperties(updateValues, originalSourceFormat);
    SourceFormat updated = repository.save(originalSourceFormat);
    return mapper.mapSourceFormatToSourceFormatDto(updated);
  }

  private void updateProperties(
      @NonNull SourceFormat newOne,
      @NonNull SourceFormat oldOne) {
    if (!oldOne.getName().equals(newOne.getName())) {
      oldOne.setName(newOne.getName());
    }
  }

  private void validateInputSourceFormat(SourceFormat sf) {
    Set<ConstraintViolation<SourceFormat>> violations = validator.validate(
        sf,
        SourceFormatUpdateValidationGroup.class);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void validateInput(SourceFormatDto dto) {
    Set<ConstraintViolation<SourceFormatDto>> violations = validator.validate(
        dto,
        SourceFormatUpdateValidationGroup.class);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void trimDtoValues(SourceFormatDto dto) {
    if (dto.getName() != null && !dto.getName().isEmpty() && !dto.getName().isBlank()) {
      dto.setName(dto.getName().trim());
    }
  }

}
