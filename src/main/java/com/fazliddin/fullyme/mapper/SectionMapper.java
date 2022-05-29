package com.fazliddin.fullyme.mapper;

import com.fazliddin.fullyme.entity.Section;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionRespDto toSectionRespDto(Section section);
    SectionDto toSectionDto(Section section);

}
