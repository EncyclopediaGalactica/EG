package com.encyclopediagalactica.sourceformats.services;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.entities.validation.SourceFormatAddValidationGroup;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class AddServiceImpl implements AddServiceInterface {

  private final SourceFormatRepository repository;
  private final SourceFormatMapperInterface mapper;
  private final Validator validator;

  public AddServiceImpl(@NonNull SourceFormatRepository repository, @NonNull SourceFormatMapperInterface mapper) {
    this.repository = repository;
    this.mapper = mapper;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Override
  public SourceFormatDto add(@NonNull SourceFormatDto dto) {

    this.trimDtoStringProperties(dto);
    this.validateInputDto(dto);
    SourceFormat sourceFormat = mapper.mapSourceFormatDtoToSourceFormat(dto);

    this.validateEntity(sourceFormat);
    SourceFormat result = repository.save(sourceFormat);

    return mapper.mapSourceFormatToSourceFormatDto(result);
  }

  private void trimDtoStringProperties(SourceFormatDto dto) {
    if (dto.getName() != null && !dto.getName().isEmpty() && !dto.getName().isBlank()) {
      dto.setName(dto.getName().trim());
    }
  }

  private void validateInputDto(SourceFormatDto dto) {
    Set<ConstraintViolation<SourceFormatDto>> violations = validator.validate(
        dto,
        SourceFormatAddValidationGroup.class);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void validateEntity(SourceFormat sourceFormat) {
    Set<ConstraintViolation<SourceFormat>> violations = validator.validate(
        sourceFormat,
        SourceFormatAddValidationGroup.class);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
