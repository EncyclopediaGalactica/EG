package com.encyclopediagalactica.services.sourceformat.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import org.springframework.stereotype.Service;

@Service
public class SourceFormatMapper implements SourceFormatMapperInterface {

  @Override
  public SourceFormatDto mapSourceFormatToSourceFormatDto(SourceFormat sourceFormat) {
    SourceFormatDto dto = new SourceFormatDto();
    dto.setId(sourceFormat.getId());
    dto.setName(sourceFormat.getName());

    return dto;
  }

  @Override
  public List<SourceFormatDto> mapSourceFormatsToSourceFormatDtos(List<SourceFormat> sourceFormats) {
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

}
