package com.encyclopediagalactica.services.sourceformat.mappers.interfaces;

import java.util.List;
import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;

public interface SourceFormatMapperInterface {
  SourceFormatDto mapSourceFormatToSourceFormatDto(SourceFormat sourceFormat);

  List<SourceFormatDto> mapSourceFormatsToSourceFormatDtos(List<SourceFormat> sourceFormats);

  List<SourceFormat> mapSourceFormatDtosToSourceFormats(List<SourceFormatDto> dtos);

  SourceFormat mapSourceFormatDtoToSourceFormat(SourceFormatDto dto);
}
