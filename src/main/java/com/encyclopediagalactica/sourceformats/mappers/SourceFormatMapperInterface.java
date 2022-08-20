package com.encyclopediagalactica.sourceformats.mappers;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;

public interface SourceFormatMapperInterface {
  SourceFormatDto mapSourceFormatToSourceFormatDto(SourceFormat sourceFormat);

  List<SourceFormatDto> mapSourceFormatsToSourceFormatDtos(List<SourceFormat> sourceFormats);

  List<SourceFormat> mapSourceFormatDtosToSourceFormats(List<SourceFormatDto> dtos);

  SourceFormat mapSourceFormatDtoToSourceFormat(SourceFormatDto dto);
}
