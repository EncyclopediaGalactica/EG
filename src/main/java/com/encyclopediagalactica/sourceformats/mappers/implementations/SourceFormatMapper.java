package com.encyclopediagalactica.sourceformats.mappers.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import com.sun.istack.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SourceFormatMapper implements SourceFormatMapperInterface {

  @Override
  public SourceFormatDto mapSourceFormatToSourceFormatDto(
      @NonNull SourceFormat sourceFormat) {
    SourceFormatDto dto = new SourceFormatDto();
    dto.setId(sourceFormat.getId());
    dto.setName(sourceFormat.getName());

    return dto;
  }

  @Override
  public List<SourceFormatDto> mapSourceFormatsToSourceFormatDtos(
      @NonNull List<SourceFormat> sourceFormats) {
    List<SourceFormatDto> dtos = new ArrayList<>();
    if (!sourceFormats.isEmpty()) {
      for (SourceFormat sourceFormat : sourceFormats) {
        SourceFormatDto singleDto = mapSourceFormatToSourceFormatDto(sourceFormat);
        dtos.add(singleDto);
      }
      return dtos;
    }
    return Collections.emptyList();
  }

  @Override
  public List<SourceFormat> mapSourceFormatDtosToSourceFormats(
      @NotNull List<SourceFormatDto> dtos) {
    List<SourceFormat> sourceFormats = new ArrayList<>();
    if (!dtos.isEmpty()) {
      for (SourceFormatDto dto : dtos) {
        SourceFormat sourceFormat = mapSourceFormatDtoToSourceFormat(dto);
        sourceFormats.add(sourceFormat);
      }
    }
    return sourceFormats;
  }

  @Override
  public SourceFormat mapSourceFormatDtoToSourceFormat(
      @NotNull SourceFormatDto dto) {
    SourceFormat sourceFormat = new SourceFormat();

    sourceFormat.setId(dto.getId());
    sourceFormat.setName(dto.getName());

    return sourceFormat;
  }

}
