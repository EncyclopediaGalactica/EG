package com.encyclopediagalactica.sourceformats.services.interfaces;


import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;

/**
 * GetAllServiceInterface.
 */
public interface GetAllServiceInterface {

  /**
   * It returns list of SourceFormatDtos representing entities in the database
   * 
   * @return list of SourceFormatDtos
   */
  List<SourceFormatDto> getAll();
}
