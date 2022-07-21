package com.encyclopediagalactica.services.sourceformat.mappers;

import java.util.List;
import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;

public interface SourceFormatMapperInterface {
  SourceFormatDto mapSourceFormatToSourceFormatDto(SourceFormat sourceFormat);

  List<SourceFormatDto> mapSourceFormatsToSourceFormatDtos(List<SourceFormat> sourceFormats);

  SourceFormat mapSourceFormatDtoToSourceFormat(SourceFormatDto dto);
}
